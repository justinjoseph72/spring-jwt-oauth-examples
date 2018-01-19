package com.justin.app.demooauthcloudclient.config;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean(name = "basicTemplate")
    public RestTemplate getBasicRestTemplate(){
        RestTemplate restTemplate = new RestTemplate(getHttpComponentsClientHttpRequestFactory());
        return restTemplate;
    }

     @Bean
    public HttpComponentsClientHttpRequestFactory getHttpComponentsClientHttpRequestFactory() {
        //.setSSLHostnameVerifier(new NoopHostnameVerifier())
        CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        return requestFactory;
    }


}
