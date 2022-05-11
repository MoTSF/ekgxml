package com.lemonbada.ekgxml.config;

import nl.altindag.ssl.SSLFactory;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ESConfig {

    @Autowired
    private EKGXMLConfig ekgxmlConfiguration;

    @Bean
    public RestHighLevelClient restHighLevelClient() {


        SSLFactory sslFactory = SSLFactory.builder()
                .withUnsafeTrustMaterial()
                .withUnsafeHostnameVerifier()
                .build();

        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(
                ekgxmlConfiguration.getEs().getUserName(), ekgxmlConfiguration.getEs().getPassword()));

        return new RestHighLevelClient(RestClient.builder(
                new HttpHost(ekgxmlConfiguration.getEs().getHost(),
                        ekgxmlConfiguration.getEs().getPort(),
                        "https"),
                        new HttpHost(ekgxmlConfiguration.getEs().getHost(),
                                ekgxmlConfiguration.getEs().getPort(),
                                "http"))
                .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setSSLContext(sslFactory.getSslContext())
                        .setSSLHostnameVerifier(sslFactory.getHostnameVerifier())
                        .setDefaultCredentialsProvider(credentialsProvider))
        );
    }
}
