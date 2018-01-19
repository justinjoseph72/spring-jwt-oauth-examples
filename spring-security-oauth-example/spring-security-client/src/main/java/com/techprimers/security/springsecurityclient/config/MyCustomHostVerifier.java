package com.techprimers.security.springsecurityclient.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

@Slf4j
@Component
public class MyCustomHostVerifier implements HostnameVerifier {
    @Override
    public boolean verify(final String s, final SSLSession sslSession) {
        log.info("The hostname is {}", sslSession.getPeerHost());
        if (sslSession.getPeerHost().equals("localhost")) {
            return true;
        }
        return false;
    }

}
