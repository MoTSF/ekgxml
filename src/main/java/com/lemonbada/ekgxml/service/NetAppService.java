package com.lemonbada.ekgxml.service;

import com.lemonbada.ekgxml.config.EKGXMLConfig;
import com.lemonbada.ekgxml.dto.NetApp;
import com.lemonbada.ekgxml.helper.NetAppIF;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.annotation.Obsolete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Deprecated
public class NetAppService {

    @Autowired
    private EKGXMLConfig ekgxmlConfiguration;

    @Autowired
    private NetAppIF netAppIF;

    @Obsolete
    public void uploadFile(Path path) {

    }

    public String createBucket() {
        NetApp.BucketResponse bucketResponse = netAppIF.createBucket(
                ekgxmlConfiguration.getUploader().getSvmUUID(),
                NetApp.BucketRequest.builder()
                        .bucketName(ekgxmlConfiguration.getUploader().getBucketName())
                        .build());

        bucketResponse.throwErrorIfPresent();

        return bucketResponse.getUuid();
    }



    public NetApp.BucketResponse findBucket() {
        NetApp.BucketResponse bucketResponse = netAppIF.findBucket(
                ekgxmlConfiguration.getUploader().getSvmUUID(),
                    ekgxmlConfiguration.getUploader().getBucketUUID());

        bucketResponse.throwErrorIfPresent();

        return bucketResponse;
    }

    public String deleteBucket() {
        NetApp.BucketResponse bucketResponse = netAppIF.deleteBucket(
                ekgxmlConfiguration.getUploader().getSvmUUID(),
                ekgxmlConfiguration.getUploader().getBucketUUID());

        bucketResponse.throwErrorIfPresent();

        return bucketResponse.getName();
    }


    public void ack() {
        findBucket();
    }


}
