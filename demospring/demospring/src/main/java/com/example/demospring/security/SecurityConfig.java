package com.example.demospring.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String API_KEY_HEADER = "X-API-Key";
    private static final String REQUIRED_API_KEY = "shabarish"; // Replace with your API key value

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/authors/post", "/authors/{id}", "/books", "/books/{id}").authenticated()
                .anyRequest().permitAll()
                .and()
                .addFilter(new ApiKeyAuthenticationFilter(authenticationManager()))
                .httpBasic();
    }
}
