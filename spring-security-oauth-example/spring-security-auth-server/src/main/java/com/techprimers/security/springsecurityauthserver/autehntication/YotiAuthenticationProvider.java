package com.techprimers.security.springsecurityauthserver.autehntication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
@Slf4j
public class YotiAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        if (authentication instanceof YotiAuthenticationToken) {
            log.info("authorization server authentication");
            return doAuthentication((YotiAuthenticationToken) authentication);
        } else if (authentication instanceof YotiPrincipal) {
            log.info("endpoint authentication");
            return authentication;
        } else {
            throw new IllegalArgumentException("In valid authentication");
        }
    }

    private Authentication doAuthentication(final YotiAuthenticationToken authenticationToken) {
        log.info("The token in the authentication provider is {} which is provided by {}", authenticationToken.getCredentials().toString(), authenticationToken.getPrincipal().toString());
        /*UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authenticationToken.getPrincipal(),"jjj");
        token.setAuthenticated(true);*/
        //return token;
        YotiPrincipal principal = new YotiPrincipal("Justin", "Joseph", "jj@jj.com");
        principal.setAuthenticated(true);
       return principal;
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return true;
    }
}
