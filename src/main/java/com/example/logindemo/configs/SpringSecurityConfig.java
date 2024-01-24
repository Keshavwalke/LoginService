package com.example.logindemo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) ->{
                        try {
                            requests
                                    .anyRequest().permitAll()
                                    .and().csrf().disable();
                        }
                        catch (Exception e){
                            throw  new RuntimeException(e);
                        }}
                );
        // spring security by default makes all endpoints to implement authorization
        //By this we've disabled the spring security & only reason we implemented
        // Spring security is we needed to use Bcrypt password Encoder

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests((requests) ->
//                        requests
//                                .anyRequest().permitAll()
//                )
//                .and()
//                .csrf().disable();
//
//        return http.build();
//    }
}
