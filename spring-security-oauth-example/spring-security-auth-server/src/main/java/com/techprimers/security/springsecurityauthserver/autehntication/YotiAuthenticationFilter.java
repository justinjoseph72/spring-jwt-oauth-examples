package com.techprimers.security.springsecurityauthserver.autehntication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class YotiAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final String TOKEN_PARAM_NAME = "token";

    public YotiAuthenticationFilter(final AuthenticationManager authenticationManager,final String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String token = obtainToken(request);
        log.info(" the token is {}",token);
        YotiAuthenticationToken authRequest = new YotiAuthenticationToken(token);
        setDeatils(request,authRequest);
        return getAuthenticationManager().authenticate(authRequest);
    }

    private void setDeatils(final HttpServletRequest request, final YotiAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    private String obtainToken(final HttpServletRequest request) {
        //FIXME: input sanitise the param (e.g. char matcher whitelist and length).
        return request.getParameter(TOKEN_PARAM_NAME);
    }
}
