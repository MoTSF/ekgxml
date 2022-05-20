package com.lemonbada.ekgxml.dto;

import com.lemonbada.ekgxml.model.RestingECG;
import lombok.Builder;
import lombok.Data;

import java.nio.file.Path;

@Data
@Builder
public class TaskProcess {
    @Data
    @Builder
    public static class ParseResult {
        private Path path;
        private Boolean success;
        private String errorMessage;
        private RestingECG restingECG;
    }
}
