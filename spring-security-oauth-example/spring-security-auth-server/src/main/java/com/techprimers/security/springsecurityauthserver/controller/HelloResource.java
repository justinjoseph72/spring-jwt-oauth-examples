package com.techprimers.security.springsecurityauthserver.controller;

import com.techprimers.security.springsecurityauthserver.model.Person;
import com.techprimers.security.springsecurityauthserver.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/hello")
public class HelloResource {


    @GetMapping("/principal")
    public Principal user(@AuthenticationPrincipal Principal principal) {
        return principal;
    }

    @GetMapping
    public String hello() {
        return "Hello World";
    }


    @GetMapping("/people")
    public ResponseEntity<Response> getPeople(){
        List<Person> people = new ArrayList<>();
        people.add(new Person(1,"person1","lastName1","person1.lastname1@email.com"));
        people.add(new Person(2,"person2","lastName2","person2.lastname2@email.com"));
        people.add(new Person(3,"person3","lastName3","person3.lastname2@email.com"));
        Response response = new Response();
        response.setPeople(people);
        return new ResponseEntity<>(response, HttpStatus.OK);
     }

}
