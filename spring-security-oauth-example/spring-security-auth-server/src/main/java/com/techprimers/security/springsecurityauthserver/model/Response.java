package com.techprimers.security.springsecurityauthserver.model;

import lombok.Data;

import java.util.List;

@Data
public class Response {

    private List<Person> people;
}
