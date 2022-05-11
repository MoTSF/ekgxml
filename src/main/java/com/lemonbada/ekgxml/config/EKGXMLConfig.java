package com.lemonbada.ekgxml.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("ekgxml")
@Data
public class EKGXMLConfig {

    @ConfigurationProperties("collector")
    @Data
    public static class Collector {
        private String path;
        private String logPath;
        private Boolean retry;
    }

    @ConfigurationProperties("es")
    @Data
    public static class ES {
        private String host;
        private Integer port;
        private String userName;
        private String password;
        private Long bulkSize;
        private String indexName;
        private String mappingsLocation;
        private String settingsLocation;
    }

    @ConfigurationProperties("csv")
    @Data
    public static class CSV {
        private String path;
        private String outputPath;
        private String converter;
    }

    @ConfigurationProperties("uploader")
    @Data
    public static class Uploader {
        private String host;
        private String svmUUID;
        private String bucketName;
        private String bucketUUID;
        private String userName;
        private String password;
    }

    private Collector collector;
    private ES es;
    private CSV csv;
    private Uploader uploader;
}
