package com.onefactor.oauth.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;

import java.util.Map;

/**
 * Created on 07.03.18.
 */
@AllArgsConstructor
public class MyAccessTokenConverter implements AccessTokenConverter {

    private DefaultAccessTokenConverter defaultAccessTokenConverter;
    private ObjectMapper om;

    @Override
    public Map<String, ?> convertAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        Map answer = defaultAccessTokenConverter.convertAccessToken(token, authentication);
        Map principal = om.convertValue(authentication.getPrincipal(), Map.class);

        answer.put("principal", principal);

        return answer;
    }

    @Override
    public OAuth2AccessToken extractAccessToken(String value, Map<String, ?> map) {
        return defaultAccessTokenConverter.extractAccessToken(value, map);
    }

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        return defaultAccessTokenConverter.extractAuthentication(map);
    }
}
