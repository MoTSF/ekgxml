package com.lemonbada.ekgxml.service;

import com.lemonbada.ekgxml.config.EKGXMLConfig;
import com.lemonbada.ekgxml.dto.TaskProcess;
import com.lemonbada.ekgxml.entity.RestingECG;
import com.lemonbada.ekgxml.helper.XMLParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.*;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.stream.Stream;

@Service
@Slf4j
public class EKGXMLService {

    @Autowired
    private EKGXMLConfig ekgxmlConfiguration;

    @Autowired
    private XMLParser xmlParser;

    @Autowired
    private AWSS3Service awss3Service;

    @Autowired
    private SQLServerService sqlServerService;

    @PostConstruct
    public void construct() throws IOException {
        System.out.println("initialize....");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("finalize...");
    }

    public void check() {
        try {
            displayMessage("점검중입니다. 잠시만 기다리세요.");

            displayMessage("외부 데이터베이스 연결중입니다.");
            sqlServerService.ack();
            displayMessage("[정상] 정상정으로 연결되었습니다.");

            displayMessage("ONTAP S3 연결중입니다.");
            awss3Service.ack();
            displayMessage("[정상] 정상적으로 연결되었습니다.");

        } catch (Exception e) {
            displayMessage(e.getMessage());
        }
    }

    private void displayMessage(String message) {
        System.out.println("==============================================================");
        System.out.println(MessageFormat.format("::: {0}", message));
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

                    Path path = Paths.get(line);
                    try {
                        log.info(path.toString());
                        log.info("ONTAP S3 업로드를 시작합니다.");
                        awss3Service.uploadObject(path);
                        Files.deleteIfExists(path);
                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                    }
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

    public void collect() {

        sqlServerService.createTableIfNotExists(ekgxmlConfiguration.getCollector().getLoadTableName());

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


    private void runStream(Stream<Path> stream) {
        stream.map(Path::toFile).forEach(file -> {
            try {
                TaskProcess.ParseResult parseResult = xmlParser.parse(file);

                if (!parseResult.getSuccess()) {
                    log.error(MessageFormat.format("[파싱오류] File = {0}, Message = {1}", file.getAbsolutePath(), parseResult.getErrorMessage()));
                    return;
                }

                RestingECG restingECG = RestingECG.of(parseResult);
                sqlServerService.save(restingECG);

            }catch (Exception e){
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });
    }

    public void createBucket() {
        try {
            String bucketName =  awss3Service.createBucket();
            displayMessage(MessageFormat.format("ONTAP S3 Bucket {0}를 생성하였습니다.", ekgxmlConfiguration.getCsv().getAwss3().getBucketName()));
        } catch (Exception e) {
            displayMessage(e.getMessage());
        }
    }

    public void deleteBucket() {
        try {
            awss3Service.deleteBucket();
            displayMessage(MessageFormat.format("ONTAP S3 Bucket {0}를 삭제하였습니다.", ekgxmlConfiguration.getCsv().getAwss3().getBucketName()));
        } catch (Exception e) {
            displayMessage(e.getMessage());
        }
    }
}
