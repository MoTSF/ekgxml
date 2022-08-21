package com.lemonbada.ekgxml.mapper.destination;

import com.lemonbada.ekgxml.entity.RestingECG;
import org.apache.ibatis.annotations.*;

@Mapper
public interface DestinationMapper {
    @Select("select 'ack' ack")
    String check();


    @Select("select object_id(#{tableName})")
    String exists(String tableName);

    @Update("CREATE TABLE ${tableName} ( " +
            " id int IDENTITY(0,1) NOT NULL, " +
            " Year varchar(4) COLLATE Korean_Wansung_CI_AS NULL, " +
            " FileName varchar(200) COLLATE Korean_Wansung_CI_AS NULL, " +
            " LAKE_ID       nvarchar(200) COLLATE Korean_Wansung_CI_AS NULL, " +
            " TABLE_NM      nvarchar(200) COLLATE Korean_Wansung_CI_AS NULL, " +
            " CATEGORY_NM   nvarchar(200) COLLATE Korean_Wansung_CI_AS NULL, " +
            " SRC_TYP_CNTE  nvarchar(200) COLLATE Korean_Wansung_CI_AS NULL, " +
            " KEY_WORD      nvarchar(200) COLLATE Korean_Wansung_CI_AS NULL, " +
            " ETL_DT        datetime DEFAULT getdate() NULL, " +
            " AlsPatientId varchar(50) COLLATE Korean_Wansung_CI_AS NULL," +
            " MuseInfo varchar(MAX) COLLATE Korean_Wansung_CI_AS NULL, " +
            " PatientDemographics varchar(MAX) COLLATE Korean_Wansung_CI_AS NULL, " +
            " TestDemographics varchar(MAX) COLLATE Korean_Wansung_CI_AS NULL, " +
            " RestingECGMeasurements varchar(MAX) COLLATE Korean_Wansung_CI_AS NULL, " +
            " OriginalRestingECGMeasurements varchar(MAX) COLLATE Korean_Wansung_CI_AS NULL, " +
            " Diagnosis varchar(MAX) COLLATE Korean_Wansung_CI_AS NULL, " +
            " OriginalDiagnosis varchar(MAX) COLLATE Korean_Wansung_CI_AS NULL, " +
            " IntervalMeasurements varchar(MAX) COLLATE Korean_Wansung_CI_AS NULL, " +
            " AmplitudeMeasurements varchar(MAX) COLLATE Korean_Wansung_CI_AS NULL, " +
            " QRSTimesTypes varchar(MAX) COLLATE Korean_Wansung_CI_AS NULL, " +
            " PharmaData varchar(MAX) COLLATE Korean_Wansung_CI_AS NULL, " +
            " CreatedAt datetime DEFAULT getdate() NULL, " +
            " CONSTRAINT RESTING_ECG_PK PRIMARY KEY (id) " +
            ")")
    void create(String tableName);

    @Insert("INSERT INTO ${tableName} " +
            "(  [Year], " +
            "   FileName, " +
            "   LAKE_ID, " +
            "   TABLE_NM, " +
            "   CATEGORY_NM, " +
            "   SRC_TYP_CNTE, " +
            "   KEY_WORD, " +
            "   ETL_DT, " +
            "   AlsPatientId, " +
            "   MuseInfo, " +
            "   PatientDemographics, " +
            "   TestDemographics, " +
            "   RestingECGMeasurements, " +
            "   OriginalRestingECGMeasurements, " +
            "   Diagnosis, " +
            "   OriginalDiagnosis, " +
            "   IntervalMeasurements, " +
            "   AmplitudeMeasurements, " +
            "   QRSTimesTypes, " +
            "   PharmaData, " +
            "   CreatedAt)" +
            "VALUES(" +
            "   #{restingECG.year}, " +
            "   #{restingECG.fileName}, " +
            "   'EKG_RESTING_ECG_' + cast(IDENT_CURRENT(#{tableName}) as varchar(20)), " +
            "   'RESTING_ECG', " +
            "   'EKG', " +
            "   '신촌세브란스병원', " +
            "   NULL, " +
            "   getdate(), " +
            "   #{restingECG.AlsPatientId}, " +
            "   #{restingECG.museInfo}, " +
            "   #{restingECG.patientDemographics}, " +
            "   #{restingECG.testDemographics}, " +
            "   #{restingECG.restingECGMeasurements}, " +
            "   #{restingECG.originalRestingECGMeasurements}, " +
            "   #{restingECG.diagnosis}, " +
            "   #{restingECG.originalDiagnosis}, " +
            "   #{restingECG.intervalMeasurements}, " +
            "   #{restingECG.amplitudeMeasurements}, " +
            "   #{restingECG.qrsTimesTypes}, " +
            "   #{restingECG.pharmaData}, " +
            "   getdate())")
    int save(@Param("tableName") String tableName, @Param("restingECG") RestingECG restingECG);
}
