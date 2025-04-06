package com.magicshoes.ai_eorg.config;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/api/embeddings/**").authenticated()
                    .anyRequest().permitAll()
            )
            .oauth2ResourceServer(oauth2 ->
                oauth2.jwt(jwt -> {})
            );
        logger.info("Security configuration applied.");
        
        // Add logging for unauthorized users
        http.exceptionHandling(exceptionHandling ->
            exceptionHandling.authenticationEntryPoint(( request, response, authException) -> {
                logger.warn("Unauthorized request: {}", request.getRequestURI());
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            })
        );

        return http.build();
    }

}
