package com.lemonbada.ekgxml.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Builder;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSS3Config {

    @Autowired
    private EKGXMLConfig ekgxmlConfiguration;

    @Bean
    public AmazonS3 amazonS3(){
        return AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                        ekgxmlConfiguration.getCsv().getAwss3().getEndPoint(),
                        ekgxmlConfiguration.getCsv().getAwss3().getRegionName()
                ))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(
                        ekgxmlConfiguration.getCsv().getAwss3().getAccessKey(),
                        ekgxmlConfiguration.getCsv().getAwss3().getSecretKey()
                )))
                .build();
    }
}
