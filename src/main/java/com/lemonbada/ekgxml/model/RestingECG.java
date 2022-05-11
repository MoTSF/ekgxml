package com.lemonbada.ekgxml.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "RestingECG")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class RestingECG {

    @XmlElement(name = "MuseInfo")
    @JsonProperty(value = "MuseInfo")
    private MuseInfo museInfo;

    @XmlElement(name = "PatientDemographics")
    @JsonProperty(value = "PatientDemographics")
    private PatientDemographics patientDemographics;

    @XmlElement(name = "TestDemographics")
    @JsonProperty(value = "TestDemographics")
    private TestDemographics testDemographics;

    @XmlElement(name = "RestingECGMeasurements")
    @JsonProperty(value = "RestingECGMeasurements")
    private RestingECGMeasurements restingECGMeasurements;

    @XmlElement(name = "OriginalRestingECGMeasurements")
    @JsonProperty(value = "OriginalRestingECGMeasurements")
    private OriginalRestingECGMeasurements originalRestingECGMeasurements;

    @XmlElement(name = "Diagnosis")
    @JsonProperty(value = "Diagnosis")
    private Diagnosis diagnosis;

    @XmlElement(name = "OriginalDiagnosis")
    @JsonProperty(value = "OriginalDiagnosis")
    private Diagnosis originalDiagnosis;

    @XmlElement(name = "MeasurementMatrix")
    @JsonIgnore
    private String measurementMatrix;

    @XmlElement(name = "IntervalMeasurements")
    @JsonProperty(value = "IntervalMeasurements")
    private IntervalMeasurements intervalMeasurements;

    @XmlElement(name = "AmplitudeMeasurements")
    @JsonIgnore
    private AmplitudeMeasurements amplitudeMeasurements;

    @XmlElement(name = "QRSTimesTypes")
    @JsonProperty(value = "QRSTimesTypes")
    private QRSTimesTypes qrsTimesTypes;

    @XmlElement(name = "Waveform")
    @JsonIgnore
    private List<Waveform> waveforms;

    @XmlElement(name = "PharmaData")
    @JsonProperty(value = "PharmaData")
    private PharmaData pharmaData;

    @XmlElement(name = "DocumentProperty")
    @JsonProperty(value = "DocumentProperty")
    private DocumentProperty documentProperty;


    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class DocumentProperty {

        @XmlElement(name = "XMLPath")
        @JsonProperty(value = "XMLPath")
        private String xmlPath;

        @XmlElement(name = "CSVPath")
        @JsonProperty(value = "CSVPath")
        private String csvPath;

        @XmlElement(name = "FileName")
        @JsonProperty(value = "FileName")
        private String fileName;
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class PharmaData {

        @XmlElement(name = "PharmaRRinterval")
        @JsonProperty(value = "PharmaRRinterval")
        private String pharmaRRinterval;

        @XmlElement(name = "PharmaUniqueECGID")
        @JsonProperty(value = "PharmaUniqueECGID")
        private String pharmaUniqueECGID;

        @XmlElement(name = "PharmaPPinterval")
        @JsonProperty(value = "PharmaPPinterval")
        private String pharmaPPinterval;

        @XmlElement(name = "PharmaCartID")
        @JsonProperty(value = "PharmaCartID")
        private String pharmaCartID;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class Waveform {

        @XmlElement(name = "WaveformType")
        @JsonIgnore
        private String waveformType;

        @XmlElement(name = "WaveformStartTime")
        @JsonIgnore
        private String waveformStartTime;

        @XmlElement(name = "NumberofLeads")
        @JsonIgnore
        private String numberOfLeads;

        @XmlElement(name = "SampleType")
        @JsonIgnore
        private String sampleType;

        @XmlElement(name = "SampleBase")
        @JsonIgnore
        private String sampleBase;

        @XmlElement(name = "SampleExponent")
        @JsonIgnore
        private String sampleExponent;

        @XmlElement(name = "HighPassFilter")
        @JsonIgnore
        private String highPassFilter;

        @XmlElement(name = "LowPassFilter")
        @JsonIgnore
        private String lowPassFilter;

        @XmlElement(name = "ACFilter")
        @JsonIgnore
        private String acFilter;

        @XmlElement(name = "LeadData")
        @JsonIgnore
        private List<LeadData> leadDatas;

        @XmlAccessorType(XmlAccessType.FIELD)
        @Data
        public static class LeadData {

            @XmlElement(name = "LeadByteCountTotal")
            @JsonIgnore
            private String leadByteCountTotal;

            @XmlElement(name = "LeadTimeOffset")
            @JsonIgnore
            private String leadTimeOffset;

            @XmlElement(name = "LeadSampleCountTotal")
            @JsonIgnore
            private String leadSampleCountTotal;

            @XmlElement(name = "LeadAmplitudeUnitsPerBit")
            @JsonIgnore
            private String leadAmplitudeUnitsPerBit;

            @XmlElement(name = "LeadAmplitudeUnits")
            @JsonIgnore
            private String leadAmplitudeUnits;

            @XmlElement(name = "LeadHighLimit")
            @JsonIgnore
            private String leadHighLimit;

            @XmlElement(name = "LeadLowLimit")
            @JsonIgnore
            private String leadLowLimit;

            @XmlElement(name = "LeadID")
            @JsonIgnore
            private String leadID;

            @XmlElement(name = "LeadOffsetFirstSample")
            @JsonIgnore
            private String leadOffsetFirstSample;

            @XmlElement(name = "FirstSampleBaseline")
            @JsonIgnore
            private String firstSampleBaseline;

            @XmlElement(name = "LeadSampleSize")
            @JsonIgnore
            private String leadSampleSize;

            @XmlElement(name = "LeadOff")
            @JsonIgnore
            private String leadOff;

            @XmlElement(name = "BaselineSway")
            @JsonIgnore
            private String baselineSway;

            @XmlElement(name = "LeadDataCRC00")
            @JsonIgnore
            private String leadDataCRC00;

            @XmlElement(name = "WaveFormData")
            @JsonIgnore
            private String waveFormData;
        }

    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class QRSTimesTypes {

        @XmlElement(name = "QRS")
        @JsonIgnore
        private List<QRS> qrses;

        @XmlElement(name = "GlobalRR")
        @JsonProperty(value = "GlobalRR")
        private String globalRR;

        @XmlElement(name = "QTRGGR")
        @JsonProperty(value = "QTRGGR")
        private String qtrggr;


        @XmlAccessorType(XmlAccessType.FIELD)
        @Data
        public static class QRS {

            @XmlElement(name = "Number")
            @JsonIgnore
            private String number;

            @XmlElement(name = "Type")
            @JsonIgnore
            private String type;

            @XmlElement(name = "Time")
            @JsonIgnore
            private String time;
        }
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class AmplitudeMeasurements {

        @XmlElement(name = "AmplitudeMeasurementMode")
        @JsonIgnore
        private String amplitudeMeasurementMode;

        @XmlElement(name = "MeasuredAmplitude")
        @JsonIgnore
        private List<MeasuredAmplitude> measuredAmplitudes;

        @XmlAccessorType(XmlAccessType.FIELD)
        @Data
        public static class MeasuredAmplitude {
            @XmlElement(name = "AmplitudeMeasurementLeadID")
            @JsonIgnore
            private String amplitudeMeasurementLeadID;

            @XmlElement(name = "AmplitudeMeasurementWaveID")
            @JsonIgnore
            private String amplitudeMeasurementWaveID;

            @XmlElement(name = "AmplitudeMeasurementPeak")
            @JsonIgnore
            private String amplitudeMeasurementPeak;

            @XmlElement(name = "AmplitudeMeasurementStart")
            @JsonIgnore
            private String amplitudeMeasurementStart;

            @XmlElement(name = "AmplitudeMeasurementDuration")
            @JsonIgnore
            private String amplitudeMeasurementDuration;

            @XmlElement(name = "AmplitudeMeasurementArea")
            @JsonIgnore
            private String amplitudeMeasurementArea;

        }
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class IntervalMeasurements {

        @XmlElement(name = "IntervalMeasurementTimeResolution")
        @JsonProperty(value = "IntervalMeasurementTimeResolution")
        private String intervalMeasurementTimeResolution;

        @XmlElement(name = "IntervalMeasurementAmplitudeResolution")
        @JsonProperty(value = "IntervalMeasurementAmplitudeResolution")
        private String intervalMeasurementAmplitudeResolution;

        @XmlElement(name = "IntervalMeasurementFilter")
        @JsonProperty(value = "IntervalMeasurementFilter")
        private String intervalMeasurementFilter;

        @XmlElement(name = "IntervalMeasurementMode")
        @JsonIgnore
        private String IntervalMeasurementMode;

        @XmlElement(name = "IntervalMeasurementMethodType")
        @JsonIgnore
        private String intervalMeasurementMethodType;

        @XmlElement(name = "LeadPOnsetCalculationMethod")
        @JsonIgnore
        private String leadPOnsetCalculationMethod;

        @XmlElement(name = "LeadPOffsetCalculationMethod")
        @JsonIgnore
        private String leadPOffsetCalculationMethod;

        @XmlElement(name = "LeadQOnsetCalculationMethod")
        @JsonIgnore
        private String leadQOnsetCalculationMethod;

        @XmlElement(name = "LeadQOffsetCalculationMethod")
        @JsonIgnore
        private String leadQOffsetCalculationMethod;

        @XmlElement(name = "LeadTOffsetCalculationMethod")
        @JsonIgnore
        private String leadTOffsetCalculationMethod;

        @XmlElement(name = "MeasuredInterval")
        @JsonProperty(value = "MeasuredInterval")
        private List<MeasuredInterval> measuredIntervals;

        @XmlAccessorType(XmlAccessType.FIELD)
        @Data
        public static class MeasuredInterval {

            @XmlElement(name = "IntervalMeasurementLeadID")
            @JsonIgnore
            private String intervalMeasurementLeadID;

            @XmlElement(name = "IntervalMeasurementPOnset")
            @JsonProperty(value = "IntervalMeasurementPOnset")
            private String intervalMeasurementPOnset;

            @XmlElement(name = "IntervalMeasurementPOffset")
            @JsonProperty(value = "IntervalMeasurementPOffset")
            private String intervalMeasurementPOffset;

            @XmlElement(name = "IntervalMeasurementQOnset")
            @JsonProperty(value = "IntervalMeasurementQOnset")
            private String intervalMeasurementQOnset;

            @XmlElement(name = "IntervalMeasurementQOffset")
            @JsonProperty(value = "IntervalMeasurementQOffset")
            private String intervalMeasurementQOffset;

            @XmlElement(name = "IntervalMeasurementTOffset")
            @JsonProperty(value = "IntervalMeasurementTOffset")
            private String intervalMeasurementTOffset;
        }


    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class OriginalDiagnosis {

        @XmlElement(name = "Modality")
        @JsonProperty(value = "Modality")
        private String modality;

        @XmlElement(name = "DiagnosisStatement")
        @JsonProperty(value = "DiagnosisStatementee")
        private List<DiagnosisStatement> DiagnosisStatements;

        @XmlAccessorType(XmlAccessType.FIELD)
        @Data
        public static class DiagnosisStatement {

            @XmlElement(name = "StmtFlag")
            @JsonIgnore
            private String stmtFlag;

            @XmlElement(name = "StmtText")
            @JsonProperty(value = "StmtText")
            private String stmtText;

        }
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class Diagnosis {

        @XmlElement(name = "Modality")
        @JsonProperty(value = "Modality")
        private String modality;

        @XmlElement(name = "DiagnosisStatement")
        @JsonProperty(value = "DiagnosisStatement")
        private List<DiagnosisStatement> DiagnosisStatements;

        @XmlAccessorType(XmlAccessType.FIELD)
        @Data
        public static class DiagnosisStatement {
            @XmlElement(name = "StmtFlag")
            @JsonIgnore
            private String stmtFlag;

            @XmlElement(name = "StmtText")
            @JsonProperty(value = "StmtText")
            private String stmtText;

        }
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class OriginalRestingECGMeasurements {
        @XmlElement(name = "VentricularRate")
        @JsonProperty(value = "VentricularRate")
        private String ventricularRate;

        @XmlElement(name = "AtrialRate")
        @JsonProperty(value = "AtrialRate")
        private String atrialRate;

        @XmlElement(name = "PRInterval")
        @JsonProperty(value = "PRInterval")
        private String prInterval;

        @XmlElement(name = "QRSDuration")
        @JsonProperty(value = "QRSDuration")
        private String qrsDuration;

        @XmlElement(name = "QTInterval")
        @JsonProperty(value = "QTInterval")
        private String qtInterval;

        @XmlElement(name = "QTCorrected")
        @JsonProperty(value = "QTCorrected")
        private String qtCorrected;

        @XmlElement(name = "PAxis")
        @JsonProperty(value = "PAxis")
        private String pAxis;

        @XmlElement(name = "RAxis")
        @JsonProperty(value = "RAxis")
        private String rAxis;

        @XmlElement(name = "TAxis")
        @JsonProperty(value = "TAxis")
        private String tAxis;

        @XmlElement(name = "QRSCount")
        @JsonProperty(value = "QRSCount")
        private String qrSCount;

        @XmlElement(name = "QOnset")
        @JsonProperty(value = "QOnset")
        private String qOnset;

        @XmlElement(name = "QOffset")
        @JsonProperty(value = "QOffset")
        private String qOffset;

        @XmlElement(name = "POnset")
        @JsonProperty(value = "POnset")
        private String pOnset;

        @XmlElement(name = "POffset")
        @JsonProperty(value = "POffset")
        private String pOffset;

        @XmlElement(name = "TOffset")
        @JsonProperty(value = "TOffset")
        private String tOffset;

        @XmlElement(name = "ECGSampleBase")
        @JsonProperty(value = "ECGSampleBase")
        private String ecgSampleBase;

        @XmlElement(name = "ECGSampleExponent")
        @JsonProperty(value = "ECGSampleExponent")
        private String ecgSampleExponent;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class RestingECGMeasurements {
        @XmlElement(name = "VentricularRate")
        @JsonProperty(value = "VentricularRate")
        private String ventricularRate;

        @XmlElement(name = "AtrialRate")
        @JsonProperty(value = "AtrialRate")
        private String atrialRate;

        @XmlElement(name = "PRInterval")
        @JsonProperty(value = "PRInterval")
        private String prInterval;

        @XmlElement(name = "QRSDuration")
        @JsonProperty(value = "QRSDuration")
        private String qrsDuration;

        @XmlElement(name = "QTInterval")
        @JsonProperty(value = "QTInterval")
        private String qtInterval;

        @XmlElement(name = "QTCorrected")
        @JsonProperty(value = "QTCorrected")
        private String qtCorrected;

        @XmlElement(name = "PAxis")
        @JsonProperty(value = "PAxis")
        private String pAxis;

        @XmlElement(name = "RAxis")
        @JsonProperty(value = "RAxis")
        private String rAxis;

        @XmlElement(name = "TAxis")
        @JsonProperty(value = "TAxis")
        private String tAxis;

        @XmlElement(name = "QRSCount")
        @JsonProperty(value = "QRSCount")
        private String qrSCount;

        @XmlElement(name = "QOnset")
        @JsonProperty(value = "QOnset")
        private String qOnset;

        @XmlElement(name = "QOffset")
        @JsonProperty(value = "QOffset")
        private String qOffset;

        @XmlElement(name = "POnset")
        @JsonProperty(value = "POnset")
        private String pOnset;

        @XmlElement(name = "POffset")
        @JsonProperty(value = "POffset")
        private String pOffset;

        @XmlElement(name = "TOffset")
        @JsonProperty(value = "TOffset")
        private String tOffset;

        @XmlElement(name = "ECGSampleBase")
        @JsonProperty(value = "ECGSampleBase")
        private String ecgSampleBase;

        @XmlElement(name = "ECGSampleExponent")
        @JsonProperty(value = "ECGSampleExponent")
        private String ecgSampleExponent;

        @XmlElement(name = "QTcFrederica")
        @JsonProperty(value = "QTcFrederica")
        private String qtCFrederica;

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class PatientDemographics {
        @XmlElement(name = "PatientID")
        @JsonProperty(value = "PatientID")
        private String patientID;

        @XmlElement(name = "PatientAge")
        @JsonProperty(value = "PatientAge")
        private String patientAge;

        @XmlElement(name = "AgeUnits")
        @JsonIgnore
        private String ageUnits;

        @XmlElement(name = "DateofBirth")
        @JsonProperty(value = "DateofBirth")
        private String dateOfBirth;

        @XmlElement(name = "Gender")
        @JsonProperty(value = "Gender")
        private String gender;

        @XmlElement(name = "Race")
        @JsonProperty(value = "Race")
        private String race;

        @XmlElement(name = "HeightIN")
        @JsonIgnore
        private String heightIN;

        @XmlElement(name = "WeightLBS")
        @JsonIgnore
        private String weightLBS;

        @XmlElement(name = "PatientLastName")
        @JsonIgnore
        private String patientLastName;

        @XmlElement(name = "PatientFirstName")
        @JsonIgnore
        private String patientFirstName;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class TestDemographics {
        @XmlElement(name = "DataType")
        @JsonIgnore
        private String dataType;

        @XmlElement(name = "Site")
        @JsonIgnore
        private String site;

        @XmlElement(name = "SiteName")
        @JsonIgnore
        private String siteName;

        @XmlElement(name = "AcquisitionDevice")
        @JsonProperty(value = "AcquisitionDevice")
        private String acquisitionDevice;

        @XmlElement(name = "Status")
        @JsonProperty(value = "Status")
        private String status;

        @XmlElement(name = "EditListStatus")
        @JsonProperty(value = "EditListStatus")
        private String editListStatus;

        @XmlElement(name = "Priority")
        @JsonIgnore
        private String Priority;

        @XmlElement(name = "Location")
        @JsonProperty(value = "Location")
        private String location;

        @XmlElement(name = "LocationName")
        @JsonProperty(value = "LocationName")
        private String locationName;

        @XmlElement(name = "RoomID")
        @JsonIgnore
        private String roomID;

        @XmlElement(name = "AcquisitionTime")
        @JsonIgnore
        private String acquisitionTime;

        @XmlElement(name = "AcquisitionDate")
        @JsonProperty(value = "AcquisitionDate")
        private String acquisitionDate;

        @XmlElement(name = "CartNumber")
        @JsonIgnore
        private String cartNumber;

        @XmlElement(name = "AcquisitionSoftwareVersion")
        @JsonIgnore
        private String acquisitionSoftwareVersion;

        @XmlElement(name = "AnalysisSoftwareVersion")
        @JsonProperty(value = "AnalysisSoftwareVersion")
        private String analysisSoftwareVersion;

        @XmlElement(name = "EditTime")
        @JsonProperty(value = "EditTime")
        private String editTime;

        @XmlElement(name = "EditDate")
        @JsonIgnore
        private String editDate;

        @XmlElement(name = "OverreaderID")
        @JsonIgnore
        private String overReaderID;

        @XmlElement(name = "EditorID")
        @JsonProperty(value = "EditorID")
        private String editorID;

        @XmlElement(name = "OverreaderLastName")
        @JsonIgnore
        private String overReaderLastName;

        @XmlElement(name = "OverreaderFirstName")
        @JsonIgnore
        private String overReaderFirstName;

        @XmlElement(name = "EditorLastName")
        @JsonIgnore
        private String editorLastName;

        @XmlElement(name = "EditorFirstName")
        @JsonIgnore
        private String editorFirstName;

        @XmlElement(name = "HISStatus")
        @JsonIgnore
        private String hissStatus;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class MuseInfo {
        @XmlElement(name = "MuseVersion")
        @JsonProperty(value = "MuseVersion")
        private String museVersion;
    }


}
