package com.lemonbada.ekgxml.mapper.external;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

@Mapper
public interface ExternalDatabaseMapper {

    @Select("{ CALL HISDW.dbo.USP_GetAlsUnitNo(#{patientNumber}) }")
    @Options(statementType = StatementType.CALLABLE)
    String findResearchNumberByPatientNumber(String patientNumber);

    @Select("select 'ack' ack")
    String check();
}
