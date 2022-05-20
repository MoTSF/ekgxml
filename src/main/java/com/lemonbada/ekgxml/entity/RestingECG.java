package com.lemonbada.ekgxml.entity;

import com.lemonbada.ekgxml.dto.ESListItem;
import com.lemonbada.ekgxml.dto.TaskProcess;
import lombok.Data;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class RestingECG {
    private String year;
    private String fileName;
    private String museInfo;
    private String patientDemographics;
    private String testDemographics;
    private String restingECGMeasurements;
    private String originalRestingECGMeasurements;
    private String diagnosis;
    private String originalDiagnosis;
    private String intervalMeasurements;
    private String amplitudeMeasurements;
    private String qrsTimesTypes;
    private String pharmaData;

    public static RestingECG of (TaskProcess.ParseResult parseResult) {
        RestingECG restingECG = new RestingECG();

        restingECG.year = parseResult.getPath().getParent().getFileName().toString();
        restingECG.fileName = parseResult.getPath().getFileName().toString();

        com.lemonbada.ekgxml.model.RestingECG model = parseResult.getRestingECG();

        restingECG.museInfo = ObjectUtils.defaultIfNull(model.getMuseInfo(), "").toString();

        restingECG.patientDemographics = ObjectUtils.defaultIfNull(model.getPatientDemographics(), "").toString();
        restingECG.testDemographics = ObjectUtils.defaultIfNull(model.getTestDemographics(), "").toString();

        restingECG.restingECGMeasurements = ObjectUtils.defaultIfNull(model.getRestingECGMeasurements(), "").toString();
        restingECG.originalRestingECGMeasurements = ObjectUtils.defaultIfNull(model.getOriginalRestingECGMeasurements(), "").toString();

        restingECG.diagnosis = ObjectUtils.defaultIfNull(model.getDiagnosis(), "").toString();
        restingECG.originalDiagnosis = ObjectUtils.defaultIfNull(model.getOriginalDiagnosis(), "").toString();

        restingECG.intervalMeasurements = ObjectUtils.defaultIfNull(model.getIntervalMeasurements(), "").toString();
        restingECG.amplitudeMeasurements = ObjectUtils.defaultIfNull(model.getAmplitudeMeasurements(), "").toString();

        restingECG.qrsTimesTypes = ObjectUtils.defaultIfNull(model.getQrsTimesTypes(), "").toString();

        restingECG.pharmaData = ObjectUtils.defaultIfNull(model.getPharmaData(), "").toString();

        return restingECG;
    }
}
