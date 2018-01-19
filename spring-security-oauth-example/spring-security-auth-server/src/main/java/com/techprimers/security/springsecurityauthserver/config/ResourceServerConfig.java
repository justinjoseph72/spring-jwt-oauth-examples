package com.techprimers.security.springsecurityauthserver.config;

import com.techprimers.security.springsecurityauthserver.autehntication.YotiAuthenticationFilter;
import com.techprimers.security.springsecurityauthserver.autehntication.YotiAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.web.authentication.*;

import javax.servlet.Filter;

@EnableResourceServer
@Configuration
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private YotiAuthenticationProvider yotiAuthenticationProvider;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.requestMatchers()

                .antMatchers("/login", "/oauth/authorize")
                .and()
                .logout().clearAuthentication(true).invalidateHttpSession(true).and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                //.permitAll();
                .disable()
                .addFilterBefore(getYotiFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    private Filter getYotiFilter() {
        YotiAuthenticationFilter filter = new YotiAuthenticationFilter(authenticationManager, "/login");
        filter.setAuthenticationSuccessHandler(successHandler());
        filter.setAuthenticationFailureHandler(failureHandler());
        return filter;
    }

    private AuthenticationFailureHandler failureHandler() {
        SimpleUrlAuthenticationFailureHandler handler = new SimpleUrlAuthenticationFailureHandler();
        handler.setDefaultFailureUrl("http://localhost:8082/ui/fail");
        return handler;
    }

    private AuthenticationSuccessHandler successHandler() {
        SavedRequestAwareAuthenticationSuccessHandler handler = new SavedRequestAwareAuthenticationSuccessHandler();
        handler.setAlwaysUseDefaultTargetUrl(true);
        handler.setDefaultTargetUrl("http://localhost:8082/ui/secure");
        return handler;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.parentAuthenticationManager(authenticationManager).authenticationProvider(yotiAuthenticationProvider);
       /* auth.parentAuthenticationManager(authenticationManager)
                .inMemoryAuthentication()
                .withUser("Peter")
                .password("peter")
                .roles("USER");*/
    }

    //excluding the endpoint from oauth security
    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/rest/public/**");
    }
}
