package com.anjuke.dw.mini.service;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        authentication.getName();

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}
