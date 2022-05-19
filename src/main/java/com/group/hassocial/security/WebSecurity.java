package com.group.hassocial.security;

import com.group.hassocial.service.RegistrationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final RegistrationService registrationService;

    public WebSecurity(PasswordEncoder passwordEncoder, RegistrationService registrationService) {
        this.passwordEncoder = passwordEncoder;
        this.registrationService = registrationService;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/user/register", "/api/user/**", "/api/user/authenticate", "/api/user/*", "/matches/**", "/profile")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();

    }

}
