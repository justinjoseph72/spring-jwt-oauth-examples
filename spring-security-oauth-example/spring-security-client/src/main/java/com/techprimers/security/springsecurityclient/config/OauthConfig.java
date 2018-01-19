package com.techprimers.security.springsecurityclient.config;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

import java.util.Arrays;

@EnableOAuth2Sso
@Configuration
public class OauthConfig extends WebSecurityConfigurerAdapter{


    @Value("${security.oauth2.client.accessTokenUri}")
    private String accessTokenUri;

    @Value("${security.oauth2.client.userAuthorizationUri}")
    private String userAuthorizationUri;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login**","/hh","/fail")
                .permitAll()
                .anyRequest()

                .authenticated()

                .and().requiresChannel().and()
                //.addFilterAt(getOauthFilter(),OAuth2ClientAuthenticationProcessingFilter.class)
                .csrf().disable().logout()
        ;

    }

    @Bean
    public OAuth2ClientAuthenticationProcessingFilter getOauthFilter(){
        return new CustomOauthFiler(getClientRestTemplate());
    }

    @Bean
   // @Primary
    public OAuth2RestTemplate getClientRestTemplate() {
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(getClient(), new DefaultOAuth2ClientContext());
        restTemplate.setRequestFactory(getHttpComponentsClientHttpRequestFactory());
        return restTemplate;
    }

    private OAuth2ProtectedResourceDetails getClient() {
        AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
        details.setId("ClientId");
        details.setClientId("ClientId");
        details.setClientSecret("secret");
        details.setAccessTokenUri(accessTokenUri);
        details.setUserAuthorizationUri(userAuthorizationUri);
        details.setScope(Arrays.asList("read", "write","user_info"));
        return details;
    }

    @Bean
    public HttpComponentsClientHttpRequestFactory getHttpComponentsClientHttpRequestFactory() {
        //.setSSLHostnameVerifier(new NoopHostnameVerifier())
        CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        return requestFactory;
    }
}
