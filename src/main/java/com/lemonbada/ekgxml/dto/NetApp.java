package com.lemonbada.ekgxml.dto;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.Arrays;

public class NetApp {

    @Data
    @Builder
    public static class BucketRequest {
        private String bucketName;
    }

    @Data
    public static class BucketResponse {
        private String uuid;
        private BucketErrorResponse error;
        private String name;
        private Volume volumne;

        @Data
        public class Volume{
            private String uuid;
        }

        @Data
        public class BucketErrorResponse {
            private String target;
            private String message;
            private String code;
        }

        public void throwErrorIfPresent(){
            if(this.error == null)
                return;

            if(StringUtils.isBlank(this.error.code) &&
                    StringUtils.isBlank(this.error.message))
                return;

            String msg = MessageFormat.format("{0}:{1}",
                    this.error.code, this.error.message);

            throw new RuntimeException(msg);
        }


    }

}
