package com.example.SpringSecurityWithDBP04.Config;

import com.example.SpringSecurityWithDBP04.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

/*
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){


        httpSecurity
                .csrf(csrf -> csrf.disable()) // Add this to allow POST requests like /register
                .authorizeHttpRequests(req ->
                        req.requestMatchers(HttpMethod.POST,"/security/register").permitAll()
                                .anyRequest().authenticated()
                ).formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());

      return httpSecurity.build();
    }
*/
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable()) // Keep this disabled for Postman
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/security/register").permitAll() // Explicitly permit
                        .anyRequest().authenticated()
                )
                // Comment out formLogin() while testing with Postman to avoid HTML redirects
                // .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults()); // Use Basic Auth for Postman

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception{
        AuthenticationManagerBuilder builder=httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);

        builder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());

        return builder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
