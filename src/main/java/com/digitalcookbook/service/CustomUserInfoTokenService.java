package com.digitalcookbook.service;

import com.digitalcookbook.domain.AuthProvider;
import com.digitalcookbook.domain.User;
import com.digitalcookbook.repository.UserRepo;
import com.digitalcookbook.security.UserPrincipal;
import com.digitalcookbook.security.oauth2.OAuth2UserInfo;
import com.digitalcookbook.security.oauth2.OAuth2UserInfoFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Optional;

public class CustomUserInfoTokenService extends UserInfoTokenServices {

    private static final Logger log = LoggerFactory.getLogger(CustomUserInfoTokenService.class);

    private UserRepo userRepository;

    private AuthProvider authProvider;


    public CustomUserInfoTokenService(String userInfoEndpointUrl,
                                       String clientId,
                                       UserRepo userRepository,
                                       AuthProvider authProvider) {
        super(userInfoEndpointUrl, clientId);
        this.authProvider = authProvider;
        this.userRepository = userRepository;
    }

    @Override
    protected Object getPrincipal(Map<String, Object> attributes) {
        return processOAuth2User(attributes);
    }


    private OAuth2User processOAuth2User(Map<String, Object> attributes) {
        log.info("Attributes {}" , attributes.toString());

        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(authProvider, attributes);
        if(StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationException(new OAuth2Error("-1"), "Email not found from provider");
        }
        Optional<User> userOptional = userRepository.findOneByEmail(oAuth2UserInfo.getEmail());
        User user;
        if(userOptional.isPresent()) {
            user = userOptional.get();
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(oAuth2UserInfo);
        }
        return UserPrincipal.create(user, attributes);
    }

    private User registerNewUser(OAuth2UserInfo oAuth2UserInfo) {
        User user = new User();
        user.setProvider(authProvider);
        user.setEmailVerified(true);
        user.setFirstName(oAuth2UserInfo.getFirstName());
        user.setLastName(oAuth2UserInfo.getLastName());
        user.setEmail(oAuth2UserInfo.getEmail());

        return userRepository.save(user);
    }

    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        if(oAuth2UserInfo.getFirstName() != null && oAuth2UserInfo.getLastName() != null){
            existingUser.setFirstName(oAuth2UserInfo.getFirstName());
            existingUser.setLastName(oAuth2UserInfo.getLastName());
            existingUser = userRepository.save(existingUser);
        }
        return existingUser;
    }

}
