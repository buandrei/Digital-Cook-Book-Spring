package com.digitalcookbook.security.oauth2;

import com.digitalcookbook.domain.AuthProvider;

import java.util.Map;

/**
 * Factory class for multiple loggins
 *
 * just add your case if needed
 */


public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(AuthProvider authProvider, Map<String, Object> attributes) {
        switch (authProvider){
            case facebook:
                return new FacebookUserInfo(attributes);

            default:
                return null;
        }
    }
}
