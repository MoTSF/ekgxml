package com.lemonbada.ekgxml.config;

import com.amazonaws.ApacheHttpClientConfig;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Builder;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import nl.altindag.ssl.SSLFactory;
import nl.altindag.ssl.socket.CompositeSSLSocketFactory;
import nl.altindag.ssl.util.Apache4SslUtils;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import java.security.NoSuchAlgorithmException;

@Configuration
public class AWSS3Config {

    @Autowired
    private EKGXMLConfig ekgxmlConfiguration;

    @Bean
    public AmazonS3 amazonS3() throws NoSuchAlgorithmException {

        SSLFactory sslFactory = SSLFactory.builder()
                .withUnsafeTrustMaterial()
                .withUnsafeHostnameVerifier()
                .build();

        ClientConfiguration clientConfiguration = new ClientConfiguration();
        ApacheHttpClientConfig apacheHttpClientConfig = clientConfiguration.getApacheHttpClientConfig();
        LayeredConnectionSocketFactory layeredConnectionSocketFactory = Apache4SslUtils.toSocketFactory(sslFactory);
        apacheHttpClientConfig.setSslSocketFactory(layeredConnectionSocketFactory);

        return AmazonS3ClientBuilder.standard()
                .withClientConfiguration(clientConfiguration)
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
