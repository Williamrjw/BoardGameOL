package com.Williamrjw.game.system;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .antMatchers("/instances","/actuator/**").permitAll() // 允许访问actuator端点
                                .antMatchers("/", "/login**", "/error**").permitAll()
                                .anyRequest().authenticated()
                )
                .oauth2Login();
        return http.build();
    }
}
