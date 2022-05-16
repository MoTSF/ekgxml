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

        private AWSS3 awss3;
        @ConfigurationProperties("awsS3")
        @Data
        public static class AWSS3 {
            private String accessKey;
            private String secretKey;
            private String endPoint;
            private String regionName;
            private String bucketName;
        }
    }

    private Collector collector;
    private ES es;
    private CSV csv;
}
