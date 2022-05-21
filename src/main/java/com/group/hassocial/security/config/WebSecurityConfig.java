package com.group.hassocial.security.config;

import com.group.hassocial.security.PasswordEncoder;
import com.group.hassocial.service.RegistrationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final RegistrationService registrationService;

    public WebSecurityConfig(PasswordEncoder passwordEncoder, RegistrationService registrationService) {
        this.passwordEncoder = passwordEncoder;
        this.registrationService = registrationService;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/user/register", "/api/user/**", "/api/user/authenticate/**", "/api/user/*", "/matches/**", "/profile")
                .permitAll()
                .antMatchers("/api/user/authenticate?token=dd426d70-80b9-4e31-a13c-500285788760").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();

    }

}
