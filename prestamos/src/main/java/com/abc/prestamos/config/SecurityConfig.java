package com.abc.prestamos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(authorize -> authorize
            .anyRequest().authenticated()
        )
        //.oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));
        .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(myConverter()))
        );
    return http.build();
  }

  @Bean
  MyJwtAuthenticationConverter myConverter() {
    return new MyJwtAuthenticationConverter();
  }
}
