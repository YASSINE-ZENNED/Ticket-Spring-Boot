package com.ticket;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtauthConverter jwtauthConverter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http.csrf()
                .disable()
                .authorizeHttpRequests()
                .anyRequest()
                .authenticated();


        http.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtauthConverter);

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();

//            .authorizeRequests(authorizeRequests ->
//                authorizeRequests
//                    .antMatchers("/auth/login").permitAll()
//                    .anyRequest().authenticated()
//            )
//            .formLogin(formLogin ->
//                formLogin
//                    .loginPage("/auth/login")
//                    .permitAll()
//            )
//            .httpBasic(Customizer.withDefaults());
    }
}
