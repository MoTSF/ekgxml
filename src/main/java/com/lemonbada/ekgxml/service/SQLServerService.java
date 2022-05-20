package com.lemonbada.ekgxml.service;

import com.lemonbada.ekgxml.config.EKGXMLConfig;
import com.lemonbada.ekgxml.entity.RestingECG;
import com.lemonbada.ekgxml.mapper.SQLServerMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SQLServerService {

    @Autowired
    private SQLServerMapper sqlServerMapper;
    @Autowired
    private EKGXMLConfig ekgxmlConfig;

    String ack(){
        return sqlServerMapper.check();
    }

    int save(RestingECG restingECG){
        return sqlServerMapper.save(ekgxmlConfig.getCollector().getLoadTableName(), restingECG);
    }
    void createTableIfNotExists(String tableName) {
        String objectId = sqlServerMapper.exists(tableName);
        if(ObjectUtils.isNotEmpty(objectId))
            return;
        sqlServerMapper.create(tableName);

    }



}
