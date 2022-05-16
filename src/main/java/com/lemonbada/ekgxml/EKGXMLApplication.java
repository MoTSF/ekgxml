package com.lemonbada.ekgxml;

import com.lemonbada.ekgxml.service.EKGXMLService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

import javax.naming.directory.NoSuchAttributeException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableConfigurationProperties
@Slf4j
public class EKGXMLApplication implements CommandLineRunner {

    @Autowired
    private ConfigurableApplicationContext configurableApplicationContext;

    @Autowired
    private EKGXMLService ekgxmlService;

    @Autowired
    private ApplicationArguments applicationArguments;

    final String ARGUMENT_OPTION_NAME = "action";


    enum COMMAND_TYPE {
        CHECK("check", "작업 환경을 점검합니다."),
        COLLECT("collect", "EKG XML 수집을 시작합니다."),
        DELETE_BUCKET("deleteBucket", "ONTAP S3 Bucket을(를) 삭제합니다."),
        CREATE_BUCKET("createBucket", "ONTAP S3 Bucket을(를) 생성합니다."),

        CSV("csv", "CSV 변환작업을 시작합니다.");

        private final String command;
        private final String description;

        COMMAND_TYPE(String command, String description) {
            this.command = command;
            this.description = description;
        }

        public static COMMAND_TYPE of(String command) throws Exception {
            return Arrays.stream(COMMAND_TYPE.values())
                    .filter(commandType -> commandType.command.equalsIgnoreCase(command))
                    .findFirst().orElseThrow(NoSuchAttributeException::new);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(EKGXMLApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        try {
            if (!applicationArguments.containsOption(ARGUMENT_OPTION_NAME)) {
                usage();
                exit();
            }


            List<String> argumentValues = applicationArguments.getOptionValues(ARGUMENT_OPTION_NAME);
            if (argumentValues.stream()
                    .noneMatch(Arrays.stream(COMMAND_TYPE.values())
                            .map(commandType -> commandType.command)
                            .collect(Collectors.toList())::contains)) {
                usage();
                exit();
            }

            for (String argumentValue : argumentValues) {
                COMMAND_TYPE commandType = COMMAND_TYPE.of(argumentValue);
                switch (commandType) {
                    case CHECK:
                        ekgxmlService.check();
                        break;
                    case COLLECT:
                        ekgxmlService.collect();
                        break;
                    case CSV:
                        ekgxmlService.csv();
                        break;
                    case DELETE_BUCKET:
                        ekgxmlService.deleteBucket();
                        break;
                    case CREATE_BUCKET:
                        ekgxmlService.createBucket();
                        break;
                }
            }

        } finally {
            configurableApplicationContext.close();
        }

    }

    public void exit() {
        System.exit(SpringApplication.exit(configurableApplicationContext, () -> 0));
    }

    public void usage() {
        System.out.println("==============================================================");
        System.out.println("============================ 사용법 ===========================");
        System.out.println("$ java -jar {application}.jar --action={actionName}");
        System.out.println(" * actionName 여러개 지정 및 연속적인 실행이 가능합니다. ");
        System.out.println("--------------------------------------------------------------");
        Arrays.stream(COMMAND_TYPE.values()).forEach(commandType -> {
            System.out.println(MessageFormat.format("- {0} ({1})", commandType.command, commandType.description));
        });
        System.out.println("==============================================================");
    }
}
