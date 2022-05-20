package com.lemonbada.ekgxml.model;


import com.lemonbada.ekgxml.dto.ESListItem;
import com.lemonbada.ekgxml.util.Utils;
import lombok.Data;

import javax.rmi.CORBA.Util;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.MessageFormat;
import java.util.*;

@XmlRootElement(name = "RestingECG")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class RestingECG {

    @XmlElement(name = "MuseInfo")
    private MuseInfo museInfo;

    @XmlElement(name = "PatientDemographics")
    private PatientDemographics patientDemographics;

    @XmlElement(name = "TestDemographics")
    private TestDemographics testDemographics;

    @XmlElement(name = "RestingECGMeasurements")
    private RestingECGMeasurements restingECGMeasurements;

    @XmlElement(name = "OriginalRestingECGMeasurements")
    private OriginalRestingECGMeasurements originalRestingECGMeasurements;

    @XmlElement(name = "Diagnosis")
    private Diagnosis diagnosis;

    @XmlElement(name = "OriginalDiagnosis")
    private Diagnosis originalDiagnosis;

    @XmlElement(name = "MeasurementMatrix")
    private String measurementMatrix;

    @XmlElement(name = "IntervalMeasurements")
    private IntervalMeasurements intervalMeasurements;

    @XmlElement(name = "AmplitudeMeasurements")
    private AmplitudeMeasurements amplitudeMeasurements;

    @XmlElement(name = "QRSTimesTypes")
    private QRSTimesTypes qrsTimesTypes;

    @XmlElement(name = "Waveform")
    private List<Waveform> waveform;

    @XmlElement(name = "PharmaData")
    private PharmaData pharmaData;

    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class PharmaData {

        @XmlElement(name = "PharmaRRinterval")
        private String pharmaRRinterval;

        @XmlElement(name = "PharmaUniqueECGID")
        private String pharmaUniqueECGID;

        @XmlElement(name = "PharmaPPinterval")
        private String pharmaPPinterval;

        @XmlElement(name = "PharmaCartID")
        private String pharmaCartID;

        @Override
        public String toString() {
            List<ESListItem> list = new ArrayList<>();

            list.add(new ESListItem("PharmaRRinterval", pharmaRRinterval));
            list.add(new ESListItem("PharmaUniqueECGID", pharmaUniqueECGID));
            list.add(new ESListItem("PharmaPPinterval", pharmaPPinterval));
            list.add(new ESListItem("PharmaCartID", pharmaCartID));

            return Utils.listToESString(list);
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class Waveform {

        @XmlElement(name = "WaveformType")
        private String waveformType;

        @XmlElement(name = "WaveformStartTime")
        private String waveformStartTime;

        @XmlElement(name = "NumberofLeads")
        private String numberOfLeads;

        @XmlElement(name = "SampleType")
        private String sampleType;

        @XmlElement(name = "SampleBase")
        private String sampleBase;

        @XmlElement(name = "SampleExponent")
        private String sampleExponent;

        @XmlElement(name = "HighPassFilter")
        private String highPassFilter;

        @XmlElement(name = "LowPassFilter")
        private String lowPassFilter;

        @XmlElement(name = "ACFilter")
        private String acFilter;

        @XmlElement(name = "LeadData")
        private List<LeadData> leadDatas;

        @XmlAccessorType(XmlAccessType.FIELD)
        @Data
        public static class LeadData {

            @XmlElement(name = "LeadByteCountTotal")
            private String leadByteCountTotal;

            @XmlElement(name = "LeadTimeOffset")
            private String leadTimeOffset;

            @XmlElement(name = "LeadSampleCountTotal")
            private String leadSampleCountTotal;

            @XmlElement(name = "LeadAmplitudeUnitsPerBit")
            private String leadAmplitudeUnitsPerBit;

            @XmlElement(name = "LeadAmplitudeUnits")
            private String leadAmplitudeUnits;

            @XmlElement(name = "LeadHighLimit")
            private String leadHighLimit;

            @XmlElement(name = "LeadLowLimit")
            private String leadLowLimit;

            @XmlElement(name = "LeadID")
            private String leadID;

            @XmlElement(name = "LeadOffsetFirstSample")
            private String leadOffsetFirstSample;

            @XmlElement(name = "FirstSampleBaseline")
            private String firstSampleBaseline;

            @XmlElement(name = "LeadSampleSize")
            private String leadSampleSize;

            @XmlElement(name = "LeadOff")
            private String leadOff;

            @XmlElement(name = "BaselineSway")
            private String baselineSway;

            @XmlElement(name = "LeadDataCRC00")
            private String leadDataCRC00;

            @XmlElement(name = "WaveFormData")
            private String waveFormData;
        }
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class QRSTimesTypes {

        @XmlElement(name = "QRS")
        private List<QRS> qrses;

        @XmlElement(name = "GlobalRR")
        private String globalRR;

        @XmlElement(name = "QTRGGR")
        private String qtrggr;


        @XmlAccessorType(XmlAccessType.FIELD)
        @Data
        public static class QRS {

            @XmlElement(name = "Number")
            private String number;

            @XmlElement(name = "Type")
            private String type;

            @XmlElement(name = "Time")
            private String time;
        }

        @Override
        public String toString() {
            List<ESListItem> list = new ArrayList<>();

            if(qrses!=null) {
                qrses.forEach(qrs -> {
                    list.add(new ESListItem("QRS.Number", qrs.number));
                    list.add(new ESListItem("QRS.Type", qrs.type));
                    list.add(new ESListItem("QRS.Time", qrs.time));
                });
            }

            list.add(new ESListItem("GlobalRR", globalRR));
            list.add(new ESListItem("QTRGGR", qtrggr));


            return Utils.listToESString(list);
        }
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class AmplitudeMeasurements {

        @XmlElement(name = "AmplitudeMeasurementMode")
        private String amplitudeMeasurementMode;

        @XmlElement(name = "MeasuredAmplitude")
        private List<MeasuredAmplitude> measuredAmplitudes;

        @XmlAccessorType(XmlAccessType.FIELD)
        @Data
        public static class MeasuredAmplitude {
            @XmlElement(name = "AmplitudeMeasurementLeadID")
            private String amplitudeMeasurementLeadID;

            @XmlElement(name = "AmplitudeMeasurementWaveID")
            private String amplitudeMeasurementWaveID;

            @XmlElement(name = "AmplitudeMeasurementPeak")
            private String amplitudeMeasurementPeak;

            @XmlElement(name = "AmplitudeMeasurementStart")
            private String amplitudeMeasurementStart;

            @XmlElement(name = "AmplitudeMeasurementDuration")
            private String amplitudeMeasurementDuration;

            @XmlElement(name = "AmplitudeMeasurementArea")
            private String amplitudeMeasurementArea;

        }

        @Override
        public String toString() {
            List<ESListItem> list = new ArrayList<>();
            list.add(new ESListItem("AmplitudeMeasurementMode", amplitudeMeasurementMode));

            if(measuredAmplitudes!=null) {
                measuredAmplitudes.forEach(measuredAmplitude -> {
                    list.add(new ESListItem("MeasuredAmplitude.AmplitudeMeasurementLeadID", measuredAmplitude.amplitudeMeasurementLeadID));
                    list.add(new ESListItem("MeasuredAmplitude.AmplitudeMeasurementWaveID", measuredAmplitude.amplitudeMeasurementWaveID));
                    list.add(new ESListItem("MeasuredAmplitude.AmplitudeMeasurementPeak", measuredAmplitude.amplitudeMeasurementPeak));
                    list.add(new ESListItem("MeasuredAmplitude.AmplitudeMeasurementStart", measuredAmplitude.amplitudeMeasurementStart));
                    list.add(new ESListItem("MeasuredAmplitude.AmplitudeMeasurementDuration", measuredAmplitude.amplitudeMeasurementDuration));
                    list.add(new ESListItem("MeasuredAmplitude.AmplitudeMeasurementArea", measuredAmplitude.amplitudeMeasurementArea));
                });
            }

            return Utils.listToESString(list);
        }
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class IntervalMeasurements {

        @XmlElement(name = "IntervalMeasurementTimeResolution")
        private String intervalMeasurementTimeResolution;

        @XmlElement(name = "IntervalMeasurementAmplitudeResolution")
        private String intervalMeasurementAmplitudeResolution;

        @XmlElement(name = "IntervalMeasurementFilter")
        private String intervalMeasurementFilter;

        @XmlElement(name = "IntervalMeasurementMode")
        private String intervalMeasurementMode;

        @XmlElement(name = "IntervalMeasurementMethodType")
        private String intervalMeasurementMethodType;

        @XmlElement(name = "LeadPOnsetCalculationMethod")
        private String leadPOnsetCalculationMethod;

        @XmlElement(name = "LeadPOffsetCalculationMethod")
        private String leadPOffsetCalculationMethod;

        @XmlElement(name = "LeadQOnsetCalculationMethod")
        private String leadQOnsetCalculationMethod;

        @XmlElement(name = "LeadQOffsetCalculationMethod")
        private String leadQOffsetCalculationMethod;

        @XmlElement(name = "LeadTOffsetCalculationMethod")
        private String leadTOffsetCalculationMethod;

        @XmlElement(name = "MeasuredInterval")
        private List<MeasuredInterval> measuredIntervals;

        @XmlAccessorType(XmlAccessType.FIELD)
        @Data
        public static class MeasuredInterval {

            @XmlElement(name = "IntervalMeasurementLeadID")
            private String intervalMeasurementLeadID;

            @XmlElement(name = "IntervalMeasurementPOnset")
            private String intervalMeasurementPOnset;

            @XmlElement(name = "IntervalMeasurementPOffset")
            private String intervalMeasurementPOffset;

            @XmlElement(name = "IntervalMeasurementQOnset")
            private String intervalMeasurementQOnset;

            @XmlElement(name = "IntervalMeasurementQOffset")
            private String intervalMeasurementQOffset;

            @XmlElement(name = "IntervalMeasurementTOffset")
            private String intervalMeasurementTOffset;
        }

        @Override
        public String toString() {
            List<ESListItem> list = new ArrayList<>();
            list.add(new ESListItem("IntervalMeasurementTimeResolution", intervalMeasurementTimeResolution));
            list.add(new ESListItem("IntervalMeasurementAmplitudeResolution", intervalMeasurementAmplitudeResolution));
            list.add(new ESListItem("IntervalMeasurementFilter", intervalMeasurementFilter));
            list.add(new ESListItem("IntervalMeasurementMode", intervalMeasurementMode));
            list.add(new ESListItem("IntervalMeasurementMethodType", intervalMeasurementMethodType));
            list.add(new ESListItem("LeadPOnsetCalculationMethod", leadPOnsetCalculationMethod));
            list.add(new ESListItem("LeadPOffsetCalculationMethod", leadPOffsetCalculationMethod));
            list.add(new ESListItem("LeadQOnsetCalculationMethod", leadQOnsetCalculationMethod));
            list.add(new ESListItem("LeadQOffsetCalculationMethod", leadQOffsetCalculationMethod));
            list.add(new ESListItem("LeadTOffsetCalculationMethod", leadTOffsetCalculationMethod));

            if(measuredIntervals!=null) {
                measuredIntervals.forEach(measuredInterval -> {
                    list.add(new ESListItem("MeasuredInterval.IntervalMeasurementLeadID", measuredInterval.intervalMeasurementLeadID));
                    list.add(new ESListItem("MeasuredInterval.IntervalMeasurementPOnset", measuredInterval.intervalMeasurementPOnset));
                    list.add(new ESListItem("MeasuredInterval.IntervalMeasurementPOffset", measuredInterval.intervalMeasurementPOffset));
                    list.add(new ESListItem("MeasuredInterval.IntervalMeasurementQOnset", measuredInterval.intervalMeasurementQOnset));
                    list.add(new ESListItem("MeasuredInterval.IntervalMeasurementQOffset", measuredInterval.intervalMeasurementQOffset));
                    list.add(new ESListItem("MeasuredInterval.IntervalMeasurementTOffset", measuredInterval.intervalMeasurementTOffset));
                });
            }

            return Utils.listToESString(list);
        }


    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class OriginalDiagnosis {
        @XmlElement(name = "Modality")

        private String modality;

        @XmlElement(name = "DiagnosisStatement")
        private List<DiagnosisStatement> DiagnosisStatements;

        @XmlAccessorType(XmlAccessType.FIELD)
        @Data
        public static class DiagnosisStatement {

            @XmlElement(name = "StmtFlag")
            private String stmtFlag;

            @XmlElement(name = "StmtText")
            private String stmtText;

        }
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class Diagnosis {

        @XmlElement(name = "Modality")
        private String modality;

        @XmlElement(name = "DiagnosisStatement")
        private List<DiagnosisStatement> diagnosisStatements;

        @XmlAccessorType(XmlAccessType.FIELD)
        @Data
        public static class DiagnosisStatement {
            @XmlElement(name = "StmtFlag")
            private String stmtFlag;

            @XmlElement(name = "StmtText")
            private String stmtText;

        }
        @Override
        public String toString(){
            List<ESListItem> list = new ArrayList<>();
            list.add(new ESListItem("Modality", modality));
            if(diagnosisStatements!=null) {
                diagnosisStatements.forEach(diagnosisStatement -> {
                    list.add(new ESListItem("DiagnosisStatement.StmtFlag", diagnosisStatement.stmtFlag));
                    list.add(new ESListItem("DiagnosisStatement.StmtText", diagnosisStatement.stmtText));
                });
            }

            return Utils.listToESString(list);
        }
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class OriginalRestingECGMeasurements {
        @XmlElement(name = "VentricularRate")
        private String ventricularRate;

        @XmlElement(name = "AtrialRate")
        private String atrialRate;

        @XmlElement(name = "PRInterval")
        private String prInterval;

        @XmlElement(name = "QRSDuration")
        private String qrsDuration;

        @XmlElement(name = "QTInterval")
        private String qtInterval;

        @XmlElement(name = "QTCorrected")
        private String qtCorrected;

        @XmlElement(name = "PAxis")
        private String pAxis;

        @XmlElement(name = "RAxis")
        private String rAxis;

        @XmlElement(name = "TAxis")
        private String tAxis;

        @XmlElement(name = "QRSCount")
        private String qrsCount;

        @XmlElement(name = "QOnset")
        private String qOnset;

        @XmlElement(name = "QOffset")
        private String qOffset;

        @XmlElement(name = "POnset")
        private String pOnset;

        @XmlElement(name = "POffset")
        private String pOffset;

        @XmlElement(name = "TOffset")
        private String tOffset;

        @XmlElement(name = "ECGSampleBase")
        private String ecgSampleBase;

        @XmlElement(name = "ECGSampleExponent")
        private String ecgSampleExponent;

        @Override
        public String toString(){
            List<ESListItem> list = new ArrayList<>();
            list.add(new ESListItem("VentricularRate", ventricularRate));
            list.add(new ESListItem("AtrialRate", atrialRate));
            list.add(new ESListItem("PRInterval", prInterval));
            list.add(new ESListItem("QRSDuration", qrsDuration));
            list.add(new ESListItem("QTInterval", qtInterval));
            list.add(new ESListItem("QTCorrected", qtCorrected));
            list.add(new ESListItem("PAxis", pAxis));
            list.add(new ESListItem("RAxis", rAxis));
            list.add(new ESListItem("TAxis", tAxis));
            list.add(new ESListItem("QRSCount", qrsCount));
            list.add(new ESListItem("QOnset", qOnset));
            list.add(new ESListItem("QOffset", qOffset));
            list.add(new ESListItem("POnset", pOnset));
            list.add(new ESListItem("POffset", pOffset));
            list.add(new ESListItem("TOffset", tOffset));
            list.add(new ESListItem("ECGSampleBase", ecgSampleBase));
            list.add(new ESListItem("ECGSampleExponent", ecgSampleExponent));

            return Utils.listToESString(list);
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class RestingECGMeasurements {
        @XmlElement(name = "VentricularRate")
        private String ventricularRate;

        @XmlElement(name = "AtrialRate")
        private String atrialRate;

        @XmlElement(name = "PRInterval")
        private String prInterval;

        @XmlElement(name = "QRSDuration")
        private String qrsDuration;

        @XmlElement(name = "QTInterval")
        private String qtInterval;

        @XmlElement(name = "QTCorrected")
        private String qtCorrected;

        @XmlElement(name = "PAxis")
        private String pAxis;

        @XmlElement(name = "RAxis")
        private String rAxis;

        @XmlElement(name = "TAxis")
        private String tAxis;

        @XmlElement(name = "QRSCount")
        private String qrsCount;

        @XmlElement(name = "QOnset")
        private String qOnset;

        @XmlElement(name = "QOffset")
        private String qOffset;

        @XmlElement(name = "POnset")
        private String pOnset;

        @XmlElement(name = "POffset")
        private String pOffset;

        @XmlElement(name = "TOffset")
        private String tOffset;

        @XmlElement(name = "ECGSampleBase")
        private String ecgSampleBase;

        @XmlElement(name = "ECGSampleExponent")
        private String ecgSampleExponent;

        @XmlElement(name = "QTcFrederica")
        private String qtCFrederica;


        @Override
        public String toString(){
            List<ESListItem> list = new ArrayList<>();
            list.add(new ESListItem("VentricularRate", ventricularRate));
            list.add(new ESListItem("AtrialRate", atrialRate));
            list.add(new ESListItem("PRInterval", prInterval));
            list.add(new ESListItem("QRSDuration", qrsDuration));
            list.add(new ESListItem("QTInterval", qtInterval));
            list.add(new ESListItem("QTCorrected", qtCorrected));
            list.add(new ESListItem("PAxis", pAxis));
            list.add(new ESListItem("RAxis", rAxis));
            list.add(new ESListItem("TAxis", tAxis));
            list.add(new ESListItem("QRSCount", qrsCount));
            list.add(new ESListItem("QOnset", qOnset));
            list.add(new ESListItem("QOffset", qOffset));
            list.add(new ESListItem("POnset", pOnset));
            list.add(new ESListItem("POffset", pOffset));
            list.add(new ESListItem("TOffset", tOffset));
            list.add(new ESListItem("ECGSampleBase", ecgSampleBase));
            list.add(new ESListItem("ECGSampleExponent", ecgSampleExponent));
            list.add(new ESListItem("QTcFrederica", qtCFrederica));

            return Utils.listToESString(list);
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class PatientDemographics {
        @XmlElement(name = "PatientID")
        private String patientID;

        @XmlElement(name = "PatientAge")
        private String patientAge;

        @XmlElement(name = "AgeUnits")
        private String ageUnits;

        @XmlElement(name = "DateofBirth")
        private String dateOfBirth;

        @XmlElement(name = "Gender")
        private String gender;

        @XmlElement(name = "Race")
        private String race;

        @XmlElement(name = "HeightIN")
        private String heightIN;

        @XmlElement(name = "WeightLBS")
        private String weightLBS;

        @XmlElement(name = "PatientLastName")
        private String patientLastName;

        @XmlElement(name = "PatientFirstName")
        private String patientFirstName;

        @Override
        public String toString(){
            List<ESListItem> list = new ArrayList<>();
            list.add(new ESListItem("PatientID", patientID));
            list.add(new ESListItem("PatientAge", patientAge));
            list.add(new ESListItem("AgeUnits", ageUnits));
            list.add(new ESListItem("DateofBirth", dateOfBirth));
            list.add(new ESListItem("Gender", gender));
            list.add(new ESListItem("Race", race));
            list.add(new ESListItem("HeightIN", heightIN));
            list.add(new ESListItem("WeightLBS", weightLBS));
            list.add(new ESListItem("PatientLastName", patientLastName));
            list.add(new ESListItem("PatientFirstName", patientFirstName));

            return Utils.listToESString(list);
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class TestDemographics {
        @XmlElement(name = "DataType")
        private String dataType;

        @XmlElement(name = "Site")
        private String site;

        @XmlElement(name = "SiteName")
        private String siteName;

        @XmlElement(name = "AcquisitionDevice")
        private String acquisitionDevice;

        @XmlElement(name = "Status")
        private String status;

        @XmlElement(name = "EditListStatus")
        private String editListStatus;

        @XmlElement(name = "Priority")
        private String priority;

        @XmlElement(name = "Location")
        private String location;

        @XmlElement(name = "LocationName")
        private String locationName;

        @XmlElement(name = "RoomID")
        private String roomID;

        @XmlElement(name = "AcquisitionTime")
        private String acquisitionTime;

        @XmlElement(name = "AcquisitionDate")
        private String acquisitionDate;

        @XmlElement(name = "CartNumber")
        private String cartNumber;

        @XmlElement(name = "AcquisitionSoftwareVersion")
        private String acquisitionSoftwareVersion;

        @XmlElement(name = "AnalysisSoftwareVersion")
        private String analysisSoftwareVersion;

        @XmlElement(name = "EditTime")
        private String editTime;

        @XmlElement(name = "EditDate")
        private String editDate;

        @XmlElement(name = "OverreaderID")
        private String overreaderID;

        @XmlElement(name = "EditorID")
        private String editorID;

        @XmlElement(name = "OverreaderLastName")
        private String overreaderLastName;

        @XmlElement(name = "OverreaderFirstName")
        private String overreaderFirstName;

        @XmlElement(name = "EditorLastName")
        private String editorLastName;

        @XmlElement(name = "EditorFirstName")
        private String editorFirstName;

        @XmlElement(name = "HISStatus")
        private String hisStatus;


        @Override
        public String toString(){
            List<ESListItem> list = new ArrayList<>();
            list.add(new ESListItem("DataType", dataType));
            list.add(new ESListItem("Site", site));
            list.add(new ESListItem("SiteName", siteName));
            list.add(new ESListItem("AcquisitionDevice", acquisitionDevice));
            list.add(new ESListItem("Status", status));
            list.add(new ESListItem("EditListStatus", editListStatus));
            list.add(new ESListItem("Priority", priority));
            list.add(new ESListItem("Location", location));
            list.add(new ESListItem("LocationName", locationName));
            list.add(new ESListItem("RoomID", roomID));
            list.add(new ESListItem("AcquisitionTime", acquisitionTime));
            list.add(new ESListItem("AcquisitionDate", acquisitionDate));
            list.add(new ESListItem("CartNumber", cartNumber));
            list.add(new ESListItem("AcquisitionSoftwareVersion", acquisitionSoftwareVersion));
            list.add(new ESListItem("AnalysisSoftwareVersion", analysisSoftwareVersion));
            list.add(new ESListItem("EditTime", editTime));
            list.add(new ESListItem("EditDate", editDate));
            list.add(new ESListItem("OverreaderID", overreaderID));
            list.add(new ESListItem("EditorID", editorID));
            list.add(new ESListItem("OverreaderLastName", overreaderLastName));
            list.add(new ESListItem("OverreaderFirstName", overreaderFirstName));
            list.add(new ESListItem("EditorLastName", editorLastName));
            list.add(new ESListItem("EditorFirstName", editorFirstName));
            list.add(new ESListItem("HISStatus", hisStatus));

            return Utils.listToESString(list);
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class MuseInfo {
        @XmlElement(name = "MuseVersion")
        private String museVersion;
        @Override
        public String toString(){
            List<ESListItem> list = new ArrayList<>();
            list.add(new ESListItem("MuseVersion", museVersion));

            return Utils.listToESString(list);
        }
    }
}
