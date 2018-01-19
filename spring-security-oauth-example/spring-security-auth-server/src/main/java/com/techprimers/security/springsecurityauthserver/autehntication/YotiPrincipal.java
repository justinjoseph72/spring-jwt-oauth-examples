package com.techprimers.security.springsecurityauthserver.autehntication;

import lombok.Value;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Value
public class YotiPrincipal extends AbstractAuthenticationToken {
    private final String firstName;
    private final String lastName;
    private final String email;

    public YotiPrincipal(final String firstName,final String lastName, final String email){
        super(Arrays.asList(new GrantedAuthority() {
            @Override
            public String getAuthority() {
               return "ROLE_USER";
            }
        }));
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

    }
    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }


}
