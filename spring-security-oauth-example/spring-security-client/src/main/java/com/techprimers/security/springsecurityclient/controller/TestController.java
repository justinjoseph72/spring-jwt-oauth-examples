package com.techprimers.security.springsecurityclient.controller;

import com.techprimers.security.springsecurityclient.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.Map;

@Controller
public class TestController {

    @Autowired
    @Qualifier(value = "basicRestTempalate")
    private RestTemplate restTemplate;

 /*   @Autowired
    OAuth2RestTemplate restOperations;*/

    @RequestMapping(path = "/hh")
    public String getPage(Map<String,Object> model) {
        OAuth2Authentication authentication = (OAuth2Authentication)SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getPrincipal());
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)authentication.getDetails();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer " +details.getTokenValue());
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> helloEntity = restTemplate.exchange("http://localhost:8083/auth/rest/hello",HttpMethod.GET,entity,String.class);
        System.out.println(helloEntity.getBody());
        model.put("messageText",helloEntity.getBody());
        ResponseEntity<Response> responseEntity = restTemplate.exchange("http://localhost:8083/auth/rest/hello/people",HttpMethod.GET,entity,Response.class);
        System.out.println(responseEntity.getBody().getPeople().size());
        model.put("people",responseEntity.getBody().getPeople());
        String bb = "Peter:peter";
        String encoded = Base64.getEncoder().encodeToString(bb.getBytes());
        HttpHeaders newHeaders = new HttpHeaders();
        newHeaders.add("Authorization","Basic "+encoded);
        HttpEntity<String> newEntity = new HttpEntity<>(newHeaders);
        helloEntity = restTemplate.exchange("http://localhost:8083/auth/rest/public/name",HttpMethod.GET,null,String.class);
        System.out.println(helloEntity.getBody());
        return "message";
    }

}
