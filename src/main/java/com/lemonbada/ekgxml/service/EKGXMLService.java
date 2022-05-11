package com.lemonbada.ekgxml.service;

import com.lemonbada.ekgxml.config.EKGXMLConfig;
import com.lemonbada.ekgxml.dto.NetApp;
import com.lemonbada.ekgxml.dto.TaskProcess;
import com.lemonbada.ekgxml.entity.ProcessLog;
import com.lemonbada.ekgxml.helper.XMLParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.*;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

@Service
@Slf4j
public class EKGXMLService {

    @Autowired
    private EKGXMLConfig ekgxmlConfiguration;

    @Autowired
    private XMLParser xmlParser;

    @Autowired
    private NetAppService netAppService;

    @Autowired
    private LocalDataBaseService localDataBaseService;

    @Autowired
    private ExternalDataBaseService externalDataBaseService;

    @Autowired
    private ESService esService;


    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    public void construct() throws IOException {
        System.out.println("initialize....");
        localDataBaseService.config();
    }

    @PreDestroy
    public void destroy() {
        System.out.println("finalize...");
    }

    public void check() {
        try {
            displayMessage("점검중입니다. 잠시만 기다리세요.");

            displayMessage("외부 데이터베이스 연결중입니다.");
            externalDataBaseService.ack();
            displayMessage("[정상] 정상정으로 연결되었습니다.");

            displayMessage("ES 연결중입니다.");
            esService.ack();
            displayMessage("[정상] 정상적으로 연결되었습니다.");

            displayMessage("NetApp 연결중입니다.");
            netAppService.ack();
            displayMessage("[정상] 정상적으로 연결되었습니다.");

        } catch (Exception e) {
            displayMessage(e.getMessage());
        }
    }

    public void cleanCache() {
        try {
            localDataBaseService.deleteAll();
            displayMessage("로컬 캐시를 삭제하였습니다.");
        } catch (Exception e) {
            displayMessage(e.getMessage());
        }

    }

    private void displayMessage(String message) {
        System.out.println("==============================================================");
        System.out.println(MessageFormat.format("::: {0}", message));
        System.out.println("==============================================================");
    }

    public void createIndex() {
        try {
            esService.createIndex();
            displayMessage(MessageFormat.format("인덱스({0})를 생성하였습니다.", ekgxmlConfiguration.getEs().getIndexName()));
        } catch (Exception e) {
            displayMessage(e.getMessage());
        }
    }

    public void showCache() {
        HashMap<String, Long> statMap = localDataBaseService.getStat();
        System.out.println("==============================================================");
        System.out.println(MessageFormat.format("캐시 : {0}, 분석 : {1}, 색인 : {2}",
                statMap.get("total"),
                statMap.get("parse"),
                statMap.get("es")));
        System.out.println("==============================================================");
    }

    public void csv() {

        try {
            displayMessage("CSV 생성을 시작합니다. 파일의 개수에 따라서 작업시간이 길어질 수 있습니다.");

            CommandLine commandLine = new CommandLine(ekgxmlConfiguration.getCsv().getConverter());
            commandLine.addArgument(ekgxmlConfiguration.getCsv().getPath());
            commandLine.addArgument(ekgxmlConfiguration.getCsv().getOutputPath());


            DefaultExecutor defaultExecutor = new DefaultExecutor();
            ExecuteWatchdog executeWatchdog = new ExecuteWatchdog(ExecuteWatchdog.INFINITE_TIMEOUT);
            defaultExecutor.setWatchdog(executeWatchdog);
            defaultExecutor.setStreamHandler(new PumpStreamHandler(new LogOutputStream() {
                @Override
                protected void processLine(String line, int logLevel) {
                    if(!line.startsWith("[CSV]")) return;
                    line = StringUtils.replace(line,"[CSV]", "");

                    if(line.startsWith("[ERROR]")) {
                        log.error(line);
                        return;
                    }
                    log.info(line);
                    /*String fileName = FilenameUtils.getName(line);
                    String path = FilenameUtils.getPath(line);
                    Path key = Paths.get(path, fileName);*/

                    //netAppService.uploadFile(Paths.get(line));
                    /*
                    try {
                        Files.deleteIfExists(Paths.get(line));
                    } catch (IOException e) {
                        log.error("파일을 삭제할 수 없습니다.", e);
                    }*/

                }
            }));

            ProcessDestroyer processDestroyer = new ShutdownHookProcessDestroyer();

            defaultExecutor.setProcessDestroyer(processDestroyer);
            defaultExecutor.execute(commandLine);

            displayMessage("CSV 생성이 완료되었습니다.");
        } catch (Exception e) {
            displayMessage(e.getMessage());
        }
    }

    public void run() {

        try (Stream<Path> stream = Files.find(Paths.get(ekgxmlConfiguration.getCollector().getPath()),
                Integer.MAX_VALUE,
                (path, attr) -> attr.isRegularFile() && FilenameUtils.isExtension(path.toString(), "xml", "XML"))) {

            displayMessage("EKG XML 파싱 및 색인 작업을 시작합니다. XML 파일개수에 따라서 작업시간이 길어질 수 있습니다.");

            runStream(stream);
            displayMessage("EKG XML 파싱 및 색인 작업이 완료되었습니다.");
        } catch (Exception e) {
            displayMessage(e.getMessage());
        }

    }


    private void runStream(Stream<Path> stream) throws IOException, InterruptedException {
        List<TaskProcess> queue = new ArrayList<>();
        stream.map(Path::toFile).forEach(file -> {

            String checkSum = DigestUtils.md5DigestAsHex(file.getAbsolutePath().getBytes(StandardCharsets.UTF_8));

            TaskProcess taskProcess = TaskProcess.builder()
                    .checkSum(checkSum)
                    .fileName(file.getName())
                    .csvPath(ekgxmlConfiguration.getCsv().getOutputPath())
                    .xmlPath(ekgxmlConfiguration.getCollector().getPath())
                    .build();

            ProcessLog processLog = localDataBaseService.findByName(checkSum);

            if (processLog != null) {
                if (processLog.isAllDone()) {
                    log.info("[건너뛰기] 이미 처리(완료)된 파일입니다. ({})", file.getAbsolutePath());
                    return;
                } else {
                    if (ekgxmlConfiguration.getCollector().getRetry()) {
                        log.info("[재시도] 오류가 보고된 파일입니다. 재시도를 시작합니다.  ({})",
                                file.getAbsolutePath());

                        taskProcess.setRetry(true);
                        taskProcess.setRetryId(processLog.getId());

                    } else {
                        log.info("[건너뛰기] 오류가 보고된 파일입니다. 건너 뜁니다. (재시도 옵션을 확인하세요.) ({})",
                                file.getAbsolutePath());
                        return;
                    }
                }
            }

            try {
                TaskProcess.ParseResult parseResult = xmlParser.parse(file);

                if (parseResult.getSuccess()) {

                    // ESDoc esDoc = modelMapper.map(parseResult.getRestingECG(), ESDoc.class);

                    //convertPatientNumberToResearchNumber(parseResult);
                    taskProcess.setParseResult(parseResult);
                    queue.add(taskProcess);
                } else {
                    log.error(MessageFormat.format("[파싱오류] File = {0}, Message = {1}", file.getAbsolutePath(), parseResult.getErrorMessage()));
                }

                if (queue.size() >= ekgxmlConfiguration.getEs().getBulkSize()) {
                    log.info("벌크 입력을 시작압니다.");

                    //esService.bulk(queue);

                    log.info("후 처리 로깅을 시작합니다.");
                    //postBulk(queue);
                }
            }catch (Exception e){
                throw new RuntimeException(e);
            }


        });

        if (queue.size() > 0) {

            log.info("큐에 남은 작업을 처리합니다..");
            log.info("벌크 입력을 시작압니다.");
            //esService.bulk(queue);
            log.info("후 처리 로깅을 시작합니다.");
            //postBulk(queue);
        }
    }

    public void convertPatientNumberToResearchNumber(TaskProcess.ParseResult parseResult) {
        if (!parseResult.getSuccess())
            return;

        parseResult.getRestingECG().getPatientDemographics().setPatientID(
                externalDataBaseService.findResearchNumberByPatientNumber(
                        parseResult.getRestingECG().getPatientDemographics().getPatientID()
                )
        );
    }

    public void dropIndex() {
        try {
            esService.deleteIndex();
            displayMessage(MessageFormat.format("ES 인덱스({0}를 삭제하였습니다.", ekgxmlConfiguration.getEs().getIndexName()));
        } catch (Exception e) {
            displayMessage(e.getMessage());
        }
    }

    public void createBucket() {
        try {
            String uuid =  netAppService.createBucket();
            displayMessage(MessageFormat.format("AWS S3 Bucket {0}({1})를 생성하였습니다.", ekgxmlConfiguration.getUploader().getBucketName(), uuid));
        } catch (Exception e) {
            displayMessage(e.getMessage());
        }
    }

    public void deleteBucket() {
        try {
            netAppService.deleteBucket();
            displayMessage(MessageFormat.format("AWS S3 Bucket({0}를 삭제하였습니다.", ekgxmlConfiguration.getUploader().getBucketName()));
        } catch (Exception e) {
            displayMessage(e.getMessage());
        }
    }

    private void postBulk(List<TaskProcess> queue) {
        for (TaskProcess taskProcess : queue) {

            if (taskProcess.getRetry()) {
                localDataBaseService.updateLog(ProcessLog.builder()
                        .fileName(taskProcess.getCheckSum())
                        .id(taskProcess.getRetryId())
                        .parseYn(taskProcess.getParseSuccess() ? "Y" : "N")
                        .esYn(taskProcess.getEsSuccess() ? "Y" : "N")
                        .build());

                if(taskProcess.getParseSuccess() && taskProcess.getEsSuccess()) {
                    log.info("[재시도] 처리완료. 분석:{}({}) / 색인:{} ({}) / ({})",
                            taskProcess.getParseSuccess(),
                            taskProcess.getParseErrorMessage(),
                            taskProcess.getEsSuccess(),
                            taskProcess.getEsErrorMessage(),
                            taskProcess.getFileName());
                } else {
                    log.error("[재시도에러] 처리완료. 분석:{}({}) / 색인:{} ({}) / ({})",
                            taskProcess.getParseSuccess(),
                            taskProcess.getParseErrorMessage(),
                            taskProcess.getEsSuccess(),
                            taskProcess.getEsErrorMessage(),
                            taskProcess.getFileName());
                }

            } else {
                localDataBaseService.insertLog(ProcessLog.builder()
                        .fileName(taskProcess.getCheckSum())
                        .parseYn(taskProcess.getParseSuccess() ? "Y" : "N")
                        .esYn(taskProcess.getEsSuccess() ? "Y" : "N")
                        .build());

                if(taskProcess.getParseSuccess() && taskProcess.getEsSuccess()) {
                    log.info("[처리완료] 분석:{}({}) / 색인:{} ({}) / ({})",
                            taskProcess.getParseSuccess(),
                            taskProcess.getParseErrorMessage(),
                            taskProcess.getEsSuccess(),
                            taskProcess.getEsErrorMessage(),
                            taskProcess.getFileName());
                }else {
                    log.error("[색인오류] 분석:{}({}) / 색인:{} ({}) / ({})",
                            taskProcess.getParseSuccess(),
                            taskProcess.getParseErrorMessage(),
                            taskProcess.getEsSuccess(),
                            taskProcess.getEsErrorMessage(),
                            taskProcess.getFileName());
                }
            }

        }
        queue.clear();

    }
}
