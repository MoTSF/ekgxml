package com.lemonbada.ekgxml.helper;

import okhttp3.Interceptor;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class NetAppInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(
                chain.request().newBuilder()
                        .build()
        );
    }
}
