package com.lemonbada.ekgxml.model;


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
    private List<Waveform> waveforms;

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
        private String IntervalMeasurementMode;

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
        private String qrSCount;

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
        private String qrSCount;

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
        private String Priority;

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
        private String overReaderID;

        @XmlElement(name = "EditorID")
        private String editorID;

        @XmlElement(name = "OverreaderLastName")
        private String overReaderLastName;

        @XmlElement(name = "OverreaderFirstName")
        private String overReaderFirstName;

        @XmlElement(name = "EditorLastName")
        private String editorLastName;

        @XmlElement(name = "EditorFirstName")
        private String editorFirstName;

        @XmlElement(name = "HISStatus")
        private String hissStatus;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class MuseInfo {
        @XmlElement(name = "MuseVersion")
        private String museVersion;
    }


}
