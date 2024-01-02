package com.confra.api.main.config.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                            .requestMatchers(mvc.pattern("confra/api/v1/user/create")).permitAll()
                            .requestMatchers(mvc.pattern("root/confra/api/v1/user/create-admin")).hasRole("ADMIN")
                            .requestMatchers(mvc.pattern("root/confra/api/v1/user")).hasRole("ADMIN")
                            .requestMatchers(mvc.pattern("root/confra/api/v1/user/{id}")).hasRole("ADMIN")
                            .requestMatchers(mvc.pattern("root/confra/api/v1/user/{email}")).hasRole("USER")
                            .requestMatchers(mvc.pattern("root/confra/api/v1/user/{id}")).hasRole("ADMIN")
                            .requestMatchers(mvc.pattern("root/confra/api/v1/user/sort")).hasRole("ADMIN")
                            .requestMatchers(mvc.pattern("root/confra/auth/v1/authenticate")).permitAll()
                            .requestMatchers(mvc.pattern("root/confra/api/v1/qrcode/generateByte/{id}")).hasRole("USER")
                            .requestMatchers(mvc.pattern("swagger-ui/**")).permitAll()
                            .requestMatchers(mvc.pattern("/v3/api-docs/**")).permitAll()
                            .anyRequest().authenticated()
                )
                .sessionManagement(
                        sessionManagementConfigurer -> sessionManagementConfigurer
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
