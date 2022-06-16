package com.lemonbada.ekgxml.service;

import com.lemonbada.ekgxml.config.EKGXMLConfig;
import com.lemonbada.ekgxml.entity.RestingECG;
import com.lemonbada.ekgxml.mapper.destination.DestinationMapper;
import com.lemonbada.ekgxml.mapper.transformation.TransformationMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    @Autowired
    private DestinationMapper destinationMapper;

    @Autowired
    private TransformationMapper transformationMapper;

    @Autowired
    private EKGXMLConfig ekgxmlConfig;

    String ack(){
        transformationMapper.check();
        return destinationMapper.check();
    }

    int save(RestingECG restingECG){
        return destinationMapper.save(ekgxmlConfig.getCollector().getLoadTableName(), restingECG);
    }
    void createTableIfNotExists(String tableName) {
        String objectId = destinationMapper.exists(tableName);
        if(ObjectUtils.isNotEmpty(objectId))
            return;
        destinationMapper.create(tableName);

    }
    String convert(String patientNo){
        return transformationMapper.convert(patientNo);
    }



}
