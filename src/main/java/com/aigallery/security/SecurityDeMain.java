package com.aigallery.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityDeMain {

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public UserDetailsManager userDetailsManager(DataSource dataSource) {
                JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

                jdbcUserDetailsManager
                                .setUsersByUsernameQuery(
                                                "SELECT email AS username, password, enabled FROM user WHERE email=?");

                jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                                "SELECT u.email AS username, r.role " +
                                                "FROM users_roles ur " +
                                                "JOIN user u ON ur.user_id = u.id " +
                                                "JOIN role r ON ur.role_id = r.id " +
                                                "WHERE u.email=?");
                return jdbcUserDetailsManager;
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

                http
                                .authorizeHttpRequests(configerer -> configerer
                                                .requestMatchers("/css/**",
                                                                "/registration",
                                                                "/error/**",
                                                                "/js/**",
                                                                "/JS/**",
                                                                "/home",
                                                                "/about",
                                                                "/services",
                                                                "/contact",
                                                                "/images/**")
                                                .permitAll() // Allow static resources
                                                .requestMatchers(HttpMethod.GET, "/aigallery/**").hasRole("USER")
                                                .requestMatchers(HttpMethod.GET, "/aigallery/user/**").hasRole("USER")
                                                .anyRequest()
                                                .authenticated())
                                .formLogin(form -> form
                                                .loginPage("/login")
                                                .loginProcessingUrl("/authenticateTheUser")
                                                .defaultSuccessUrl("/aigallery/user", true)
                                                .permitAll())
                                .logout(logout -> logout
                                                .invalidateHttpSession(true)
                                                .clearAuthentication(true)
                                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                                .logoutSuccessUrl("/login?logout")
                                                .permitAll())

                                .exceptionHandling(configurer -> configurer
                                                .accessDeniedPage("/access-denied"));

                return http.build();

        }
}
