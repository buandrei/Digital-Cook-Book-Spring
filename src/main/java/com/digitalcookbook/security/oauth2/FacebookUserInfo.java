package com.digitalcookbook.security.oauth2;

import java.util.Map;

public class FacebookUserInfo extends OAuth2UserInfo {

    public FacebookUserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) attributes.get("id");
    }

    @Override
    public String getFirstName() {
        return (String) attributes.get("first_name");
    }

    @Override
    public String getLastName() {
        return (String) attributes.get("last_name");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }


}
