package com.techprimers.security.springsecurityclient.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Person {
    private final long id;
    private final String name;
    private final String lastName;
    private final String email;
}
