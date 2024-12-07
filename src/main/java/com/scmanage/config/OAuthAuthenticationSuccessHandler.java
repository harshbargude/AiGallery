package com.scmanage.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.scmanage.entities.Providers;
import com.scmanage.entities.User;
import com.scmanage.helpers.AppConstants;
import com.scmanage.repository.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);

    @Autowired
    private UserRepo userRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        logger.info("OAuthAuthenticationSuccessHandler");
        // datako database me store karsakte hai
        DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();

// Identify the provider
        var OAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        String authorizeClientRegister = OAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
        logger.info(authorizeClientRegister);

                logger.info(user.getName());
                // logger.info(user.getAttribute());
                user.getAttributes().forEach((key,value)->{
                    logger.info("{}=>{}",key,value);
                });


                String email = user.getAttribute("email").toString();
                String name = user.getAttribute("name").toString();
                String picture = user.getAttribute("picture").toString();

                User user1 = new User();
                user1.setName(name);
                user1.setEmail(email);
                user1.setProfilePic(picture);
                user1.setPassword("password");
                user1.setUserId(UUID.randomUUID().toString());
                user1.setProvider(Providers.GOOGLE);
                user1.setEnabled(true);
                user1.setEmailVerified(true);
                user1.setProviderUserId(user.getName());
                user1.setRolelist(List.of(AppConstants.ROLE_USER));
                user1.setAbout("Account is created using Google!");

                User user2 = userRepo.findByEmail(email).orElse(null);
                if(user2 == null) {
                    userRepo.save(user1);
                    logger.info("User Saved:" + email);
                }



        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
    }

}
