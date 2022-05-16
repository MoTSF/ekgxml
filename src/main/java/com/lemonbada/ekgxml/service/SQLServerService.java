package com.lemonbada.ekgxml.service;

import com.lemonbada.ekgxml.mapper.SQLServerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SQLServerService {

    @Autowired
    private SQLServerMapper externalDatabaseMapper;

    String ack(){
        return externalDatabaseMapper.check();
    }



}
