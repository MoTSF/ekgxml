package com.lemonbada.ekgxml.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.lemonbada.ekgxml.config.EKGXMLConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AWSS3Service {

    @Autowired
    private EKGXMLConfig ekgxmlConfiguration;

    @Autowired
    private AmazonS3 amazonS3;

    public String createBucket() {
        if(amazonS3.doesBucketExistV2(ekgxmlConfiguration.getCsv().getAwss3().getBucketName())) {
            throw new RuntimeException("이미 Bucket이 존재합니다.");
        }

        Bucket bucket = amazonS3.createBucket(ekgxmlConfiguration.getCsv().getAwss3().getBucketName());
        return bucket.getName();
    }



    public void deleteBucket() {

        if(!amazonS3.doesBucketExistV2(ekgxmlConfiguration.getCsv().getAwss3().getBucketName())) {
            throw new RuntimeException("Bucket이 존재하지 않습니다.");
        }
        amazonS3.deleteBucket(ekgxmlConfiguration.getCsv().getAwss3().getBucketName());
    }


    public void ack() {
        amazonS3.listBuckets();
    }


}
