package com.scmanage.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.scmanage.services.impl.SecurityCoustomUserDetailsService;

@Configuration
public class SecurityConfig {
    // user create and login using java code services within memory services

    // private InMemoryUserDetailsManager inMemoryUserDetailsManager;

    // @Bean
    // public UserDetailsService userDetailsService() {

    // UserDetails user = User
    // .withDefaultPasswordEncoder()
    // .username("admin123")
    // .password("admin123")
    // // .roles("ADMIN","USER")
    // .build();

    // UserDetails user2 = User
    // .withDefaultPasswordEncoder()
    // .username("admin456")
    // .password("admin456")
    // // .roles("ADMIN","USER")
    // .build();

    // var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user,user2);
    // return inMemoryUserDetailsManager;

    // }
    @Autowired
    private OAuthAuthenticationSuccessHandler handler;

    @Autowired
    private SecurityCoustomUserDetailsService userDetailsService;

    @Bean // Marks this method as a Spring Bean definition
    public DaoAuthenticationProvider daoauthenticationProvider() {
        // Create a DaoAuthenticationProvider instance
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        // Set the UserDetailsService to fetch user details from a database or another
        // source
        daoAuthenticationProvider.setUserDetailsService(userDetailsService); // Replace null with a proper
                                                                             // UserDetailsService
        // implementation

        // Set the PasswordEncoder to encrypt and verify passwords
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder()); // Replace null with an actual PasswordEncoder
                                                                         // like
        // BCryptPasswordEncoder

        // Return the configured authentication provider
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // configuration

        // urls configure kiay hai ki koun se public rangenge aur koun se private
        // rangenge
        httpSecurity.authorizeHttpRequests(authorize -> {
            // authorize.requestMatchers("/home", "/register", "/services").permitAll();
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });



        // form default login
        // agar hame kuch bhi change karna hua to hama yaha ayenge: form login se
        // related
        // httpSecurity.formLogin(Customizer.withDefaults());
        httpSecurity.formLogin(formLogin -> {
            //
            formLogin.loginPage("/login");
            formLogin.loginProcessingUrl("/authenticate");
            formLogin.successForwardUrl("/user/profile");
            // formLogin.failureForwardUrl("/login?error=true");
            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password");

            // formLogin.failureHandler(new AuthenticationFailureHandler() {
            //     @Override
            //     public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            //             AuthenticationException exception) throws IOException, ServletException {
            //         // TODO Auto-generated method stub
            //         throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationFailure'");
            //     }
            // });

            // formLogin.successHandler(new AuthenticationSuccessHandler(){

            //     @Override
            //     public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            //             Authentication authentication) throws IOException, ServletException {
            //         // TODO Auto-generated method stub
            //         formLogin.successForwardUrl("/user/dashboard");
            //         // throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationSuccess'");
            //     }
            // });


        });


        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.logout(logoutForm->{
            logoutForm.logoutUrl("/logout");
            logoutForm.logoutSuccessUrl("/login?logout=true");

        });

        //oauth login configyration
        // to make tha /ligin page integrate
        httpSecurity.oauth2Login(oauth->{
            oauth.loginPage("/login");
            oauth.successHandler(handler);
            // oauth

        }  
        );
    
        return httpSecurity.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
