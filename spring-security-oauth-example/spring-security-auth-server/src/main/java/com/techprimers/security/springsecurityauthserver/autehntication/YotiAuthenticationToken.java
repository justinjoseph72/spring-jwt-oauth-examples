package com.techprimers.security.springsecurityauthserver.autehntication;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class YotiAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 1L;
    private static final String PRINCIPAL = "YOTI";
    private String token;

    public YotiAuthenticationToken(final String token) {
        super(null);
        this.token = token;
        setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }

    @Override
    public Object getPrincipal() {
        return this.PRINCIPAL;
    }
}
