package com.lemonbada.ekgxml.dto;

import com.lemonbada.ekgxml.model.RestingECG;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class TaskProcess {
    @NonNull
    private String fileName;
    @NonNull
    private String xmlPath;
    @NonNull
    private String csvPath;

    private String checkSum;

    @Builder.Default
    private Boolean retry = false;
    private Long retryId;

    private RestingECG restingECG;
    @Builder.Default
    private Boolean parseSuccess = false;
    @Builder.Default
    private String parseErrorMessage="";

    @Builder.Default
    private Boolean esSuccess = false;
    @Builder.Default
    private String esErrorMessage = "";

    public void setParseResult(ParseResult parseResult){
        this.parseSuccess = parseResult.success;
        this.restingECG = parseResult.restingECG;

        RestingECG.DocumentProperty documentProperty = new RestingECG.DocumentProperty();
        documentProperty.setCsvPath(csvPath);
        documentProperty.setXmlPath(xmlPath);
        documentProperty.setFileName(fileName);
        this.restingECG.setDocumentProperty(documentProperty);

    this.parseErrorMessage = parseResult.errorMessage;
    }

    @Data
    @Builder
    public static class ParseResult {
        private Boolean success;
        private String researchNumber;
        private String errorMessage;
        private RestingECG restingECG;
    }
}
