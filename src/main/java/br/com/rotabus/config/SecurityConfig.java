package br.com.rotabus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http
            .authorizeHttpRequests(auth -> auth

                .requestMatchers(
                    "/",
                    "/login",
                    "/css/**",
                    "/js/**",
                    "/h2-console/**"
                ).permitAll()

                .requestMatchers("/admin/**")
                .hasAnyRole("ADMIN", "EMPRESA")

                .anyRequest()
                .permitAll()
            )

            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/admin", true)
                .permitAll()
            )

            .logout(logout -> logout
                .logoutSuccessUrl("/")
                .permitAll()
            )

            .csrf(csrf -> csrf
                    .ignoringRequestMatchers("/h2-console/**")
            )

            .headers(headers -> headers
                    .frameOptions(frame -> frame.sameOrigin())
            )

            .exceptionHandling(exception -> exception
                .accessDeniedHandler((request, response, ex) -> {
                    response.sendRedirect("/");
                })
            );

        return http.build();
    }
}