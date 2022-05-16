package com.lemonbada.ekgxml.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SQLServerMapper {
    @Select("select 'ack' ack")
    String check();
}
