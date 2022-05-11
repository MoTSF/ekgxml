package com.lemonbada.ekgxml.config;

import com.lemonbada.ekgxml.helper.NetAppIF;
import com.lemonbada.ekgxml.helper.NetAppInterceptor;
import nl.altindag.ssl.SSLFactory;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class NetAppConfig {

    @Autowired
    private EKGXMLConfig ekgxmlConfig;

    @Autowired
    private NetAppInterceptor retrofitInterceptor;

    @Bean
    public OkHttpClient okHttpClient() throws Exception {

        SSLFactory sslFactory = SSLFactory.builder()
                .withUnsafeTrustMaterial()
                .withUnsafeHostnameVerifier()
                .build();

        return new OkHttpClient.Builder()
                .addInterceptor(retrofitInterceptor)
                .sslSocketFactory(sslFactory.getSslSocketFactory(), sslFactory.getTrustManager().orElseThrow(Exception::new))
                .hostnameVerifier(sslFactory.getHostnameVerifier())
                .build();
    }

    @Bean
    public Retrofit retrofit(OkHttpClient okHttpClient){

        return new Retrofit.Builder()
                .baseUrl(ekgxmlConfig.getUploader().getHost())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Bean
    public NetAppIF retrofitIF(Retrofit retrofit){
        return retrofit.create(NetAppIF.class);
    }


}
