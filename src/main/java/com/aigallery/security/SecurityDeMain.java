package com.aigallery.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityDeMain {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // Queruy to retrive user name and password In main
        jdbcUserDetailsManager
                .setUsersByUsernameQuery("select name, password, enabled from users where id=?");

        jdbcUserDetailsManager
                .setAuthoritiesByUsernameQuery("select id, role from users where email=?");

        // jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
        //     "SELECT email, CONCAT('ROLE_', role) FROM users WHERE email=?"
        // );

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(configerer -> configerer
                        .requestMatchers("/css/**", "/register-user" , "/authenticate").permitAll() // Allow static resources
                        .requestMatchers("/JS/**").permitAll() // Allow static resources
                        .requestMatchers("/home").permitAll() // Allow static resources
                        .requestMatchers("/about").permitAll() // Allow static resources
                        .requestMatchers("/services").permitAll() // Allow static resources
                        .requestMatchers("/contact").permitAll() // Allow static resources
                        .requestMatchers("/register").permitAll() // Allow static resources
                        .requestMatchers(HttpMethod.GET, "/user").hasRole("USER")
                        .anyRequest()
                        .authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/authenticateTheUser")
                        .successForwardUrl("/user")
                        .permitAll())
                .logout(logout -> logout.permitAll())

                .exceptionHandling(configurer -> configurer
                        .accessDeniedPage("/access-denied"));

        return http.build();

    }
}
