package com.techprimers.security.springsecurityauthserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/public")
public class PublicController {

    @GetMapping
    public String getPublicString() {
        return "This is public api";
    }

    @GetMapping(path = "/name")
    public String getName(){
        return "Justin";
    }
}
