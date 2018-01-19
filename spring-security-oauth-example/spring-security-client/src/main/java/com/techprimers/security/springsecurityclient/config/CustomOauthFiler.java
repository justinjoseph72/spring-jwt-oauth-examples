package com.techprimers.security.springsecurityclient.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;


public class CustomOauthFiler extends OAuth2ClientAuthenticationProcessingFilter {


    public CustomOauthFiler(final OAuth2RestOperations restOperations) {
        super("/login");
        //log.info("using my custom");
        setRestTemplate(restOperations);
    }


}
