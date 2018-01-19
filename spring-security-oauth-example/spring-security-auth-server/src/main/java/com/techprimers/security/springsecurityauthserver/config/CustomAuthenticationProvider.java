package com.techprimers.security.springsecurityauthserver.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        return null;
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return false;
    }
}
