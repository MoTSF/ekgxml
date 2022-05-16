package com.lemonbada.ekgxml.dto;

import com.lemonbada.ekgxml.model.RestingECG;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskProcess {
    @Data
    @Builder
    public static class ParseResult {
        private Boolean success;
        private String errorMessage;
        private RestingECG restingECG;
    }
}
