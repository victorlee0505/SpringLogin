package com.example.demo.login.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getUserName() {
        return getAuthentication() == null ? "anonymousUser" : getAuthentication().getName();
    }

    public String getCredentials() {
        return getAuthentication() == null ? "anonymousUser" : getAuthentication().getCredentials().toString();
    }

    public String getDetails() {
        return getAuthentication() == null ? "anonymousUser" : getAuthentication().getDetails().toString();
    }

    public String getPrincipal() {
        return getAuthentication() == null ? "anonymousUser" : getAuthentication().getPrincipal().toString();
    }

    public String getUserEmail() {
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) getAuthentication();
        OidcUser oidcUser = (OidcUser) oauthToken.getPrincipal();
        return oidcUser.getPreferredUsername();
    }
}