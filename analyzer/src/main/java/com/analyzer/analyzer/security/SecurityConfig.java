package com.analyzer.analyzer.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Deprecated
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.authorizeRequests()
                .antMatchers("/h2-console/**")
                .permitAll();

        // ! Allow access to the h2-console
        http.authorizeRequests().antMatchers("/h2-console", "/h2-console/**");
        http.headers().frameOptions().sameOrigin();

        http.authorizeRequests().anyRequest().denyAll();
    }
}
