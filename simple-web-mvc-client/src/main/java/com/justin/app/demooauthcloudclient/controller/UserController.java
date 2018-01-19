package com.justin.app.demooauthcloudclient.controller;

import com.justin.app.demooauthcloudclient.config.TokenObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    @Autowired
    @Qualifier(value = "basicTemplate")
    private  RestTemplate restTemplate;

    @GetMapping("secure-page")
    public String getSecurePage(Map<String,Object> model, TokenObject tokenObject){
        log.info("the token from the service is {}",tokenObject.getToken());

        /*for(int i=0;i<request.getCookies().length;i++){
            log.info("cookieee is {}",request.getCookies()[i].getValue());
        }*/
        return "secure";
    }


}
