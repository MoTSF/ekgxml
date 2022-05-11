package com.lemonbada.ekgxml.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemonbada.ekgxml.config.EKGXMLConfig;
import com.lemonbada.ekgxml.dto.TaskProcess;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.ElasticsearchStatusException;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ESService {

    @Autowired
    private EKGXMLConfig ekgxmlConfiguration;

    @Autowired
    private RestHighLevelClient restHighLevelClient;


    @Autowired
    private ObjectMapper objectMapper;

    public void bulk(List<TaskProcess> queue) throws IOException {

        List<TaskProcess> taskProcesses = queue.stream()
                .filter(TaskProcess::getParseSuccess)
                .collect(Collectors.toList());

        if (taskProcesses.size() <= 0) return;

        BulkRequest bulkRequest = new BulkRequest();
        for (TaskProcess taskProcess : taskProcesses) {

            IndexRequest indexRequest = new IndexRequest(ekgxmlConfiguration.getEs().getIndexName());
            indexRequest.id(taskProcess.getCheckSum());

            indexRequest.source(objectMapper.writeValueAsString(
                    taskProcess.getRestingECG()), XContentType.JSON);
            bulkRequest.add(indexRequest);

            taskProcess.setEsSuccess(true);
        }

        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        if (bulkResponse.hasFailures()) {
            for (BulkItemResponse bulkItemResponse : bulkResponse.getItems()) {
                if (bulkItemResponse.isFailed()) {
                    TaskProcess dto = queue.stream()
                            .filter(item -> item.getCheckSum().equals(bulkItemResponse.getId()))
                            .findFirst().orElseThrow(NoSuchElementException::new);
                    dto.setEsSuccess(false);
                    dto.setEsErrorMessage(bulkItemResponse.getFailureMessage());
                }
            }
        }
    }

    public Boolean deleteIndex() throws IOException {

        GetIndexRequest getIndexRequest =
                new GetIndexRequest(ekgxmlConfiguration.getEs().getIndexName());
        boolean exists = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);

        if (!exists)
            throw new ElasticsearchStatusException("인덱스가 존재하지 않습니다.", RestStatus.FOUND);


        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(ekgxmlConfiguration.getEs().getIndexName());
        AcknowledgedResponse acknowledgedResponse = restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        return acknowledgedResponse.isAcknowledged();
    }

    public boolean ack() throws IOException {
        GetIndexRequest getIndexRequest =
                new GetIndexRequest(ekgxmlConfiguration.getEs().getIndexName());
        return restHighLevelClient.ping(RequestOptions.DEFAULT);

    }

    public void createIndex() throws IOException {
        GetIndexRequest getIndexRequest =
                new GetIndexRequest(ekgxmlConfiguration.getEs().getIndexName());
        boolean exists = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);

        if (exists)
            throw new ElasticsearchStatusException("이미 인덱스가 존재합니다.", RestStatus.FOUND);

        CreateIndexRequest createIndexRequest = new CreateIndexRequest(ekgxmlConfiguration.getEs().getIndexName());
        createIndexRequest.settings(
                StringUtils.toEncodedString(Files.readAllBytes(Paths.get(ekgxmlConfiguration.getEs().getSettingsLocation())), Charset.defaultCharset()),
                XContentType.JSON);

        createIndexRequest.mapping(
                StringUtils.toEncodedString(Files.readAllBytes(Paths.get(ekgxmlConfiguration.getEs().getMappingsLocation())), Charset.defaultCharset()),
                XContentType.JSON);
        restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
    }
}
