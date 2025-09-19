package com.example.quanLy_diem_danh.configuration;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        HttpSecurity httpSecurity = http
                .sessionManagement(management->
                        management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(Authorize->
                        Authorize.requestMatchers("/api/**").authenticated()
                                .anyRequest().permitAll()
                ).addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer :: disable)
                .cors(
                        cors->cors.configurationSource(corsConfigurationSource())
                );

        return httpSecurity.build();
    }

    private CorsConfigurationSource corsConfigurationSource() {
       return new CorsConfigurationSource() {
           @Override
           public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
               CorsConfiguration config = new CorsConfiguration();
               config.setAllowedOrigins(
                       Arrays.asList(
                               "http://localhost:5173",
                               "http://localhost:3000"
                       )

               );
               config.setAllowCredentials(true);
               config.setAllowedMethods(Collections.singletonList(CorsConfiguration.ALL));
               config.setAllowedHeaders(Collections.singletonList(CorsConfiguration.ALL));
               config.setExposedHeaders(Arrays.asList("Authorization", "Content-Type"));
               config.setMaxAge(3600L);
               return config;
           }
       };
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
