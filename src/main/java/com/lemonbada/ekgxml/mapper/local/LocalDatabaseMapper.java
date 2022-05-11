package com.lemonbada.ekgxml.mapper.local;

import com.lemonbada.ekgxml.entity.ProcessLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;

@Mapper
public interface LocalDatabaseMapper {

    @Update("CREATE TABLE process_log(" +
            "   id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "   file_name TEXT(100) NOT NULL," +
            "   parse_yn TEXT(1) NOT NULL DEFAULT('N')," +
            "   es_yn TEXT(1) NOT NULL DEFAULT('N')" +
            ");")
    void createTable();

    @Update("CREATE INDEX process_log_file_name_idx ON process_log (file_name);")
    void createIndex();

    @Select("SELECT count(name)>0 FROM sqlite_master WHERE type='table' and name = 'process_log'")
    Boolean existsTable();

    @Insert(("INSERT INTO process_log (" +
            "       file_name, " +
            "       parse_yn," +
            "       es_yn" +
            "   ) values (" +
            "       #{fileName}," +
            "       #{parseYn}," +
            "       #{esYn}" +
            ")"))
    int insert(ProcessLog log);

    @Update(("UPDATE process_log SET " +
            "   parse_yn = #{parseYn}, " +
            "   es_yn = #{esYn} "+
            " WHERE " +
            "   id = #{id}"))
    int updateById(ProcessLog log);

    @Select("select * from process_log where file_name = #{fileName}")
    ProcessLog findByName(String fileName);

    @Select("select " +
            "   count(id) total, " +
            "   COALESCE (sum(case parse_yn when 'Y' then 1 else 0 end), 0) parse," +
            "   COALESCE (sum(case es_yn when 'Y' then 1 else 0 end), 0) es " +
            " from process_log")
    HashMap<String, Long> getStat();

    @Update("DELETE FROM process_log")
    void deleteAll();
}
