package com.lemonbada.ekgxml.service;

import com.lemonbada.ekgxml.entity.ProcessLog;
import com.lemonbada.ekgxml.mapper.local.LocalDatabaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LocalDataBaseService {

    @Autowired
    private LocalDatabaseMapper localDatabaseMapper;

    public void config(){
        createLogTableIfNotExists();
    }

    private void createLogTableIfNotExists(){
        if(!localDatabaseMapper.existsTable()){
            localDatabaseMapper.createTable();
            localDatabaseMapper.createIndex();
        }
    }

    public int insertLog(ProcessLog log){
        return localDatabaseMapper.insert(log);
    }
    public int updateLog(ProcessLog log){
        return localDatabaseMapper.updateById(log);
    }

    public ProcessLog findByName(String fileName){
        return localDatabaseMapper.findByName(fileName);
    }

    public HashMap<String, Long> getStat(){
        return localDatabaseMapper.getStat();
    }

    public void deleteAll(){
        localDatabaseMapper.deleteAll();
    }

}
