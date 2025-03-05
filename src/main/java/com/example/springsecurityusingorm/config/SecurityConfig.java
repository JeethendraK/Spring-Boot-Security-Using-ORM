package com.example.springsecurityusingorm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    // Constructor injection to avoid circular reference
    public SecurityConfig(BCryptPasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    // AuthenticationManager bean will be automatically configured by Spring Security
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public SecurityFilterChain configureAuth(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/home", "/", "/user/**").permitAll() // Public URLs
                        .requestMatchers("/admin").hasRole("ADMIN") // Only accessible by ADMIN
                        .requestMatchers("/customer").hasRole("CUSTOMER") // Only accessible by CUSTOMER
                        .anyRequest().authenticated() // Protect all other URLs and require authentication
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login") // Custom login page
                        .permitAll() // Allow everyone to access the login page
                        .defaultSuccessUrl("/hello", true) // Redirect to /hello after successful login
                )
                .logout(logout -> logout.permitAll() // Allow everyone to log out
                );

        return http.build();
    }
}
