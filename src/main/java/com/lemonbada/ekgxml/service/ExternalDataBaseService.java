package com.lemonbada.ekgxml.service;

import com.lemonbada.ekgxml.mapper.external.ExternalDatabaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExternalDataBaseService {

    @Autowired
    private ExternalDatabaseMapper externalDatabaseMapper;

    String findResearchNumberByPatientNumber(String patientNumber){
        return externalDatabaseMapper.findResearchNumberByPatientNumber(patientNumber);
    }

    String ack(){
        return externalDatabaseMapper.check();
    }



}
