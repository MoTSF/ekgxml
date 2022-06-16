package com.lemonbada.ekgxml.mapper.transformation;

import org.apache.ibatis.annotations.*;

@Mapper
public interface TransformationMapper {
    @Select("select 'ack' ack")
    String check();

    @Select("{call dbo.USP_GetAlsUnitNo (#{patientNo})}")
    String convert(String patientNo);

}
