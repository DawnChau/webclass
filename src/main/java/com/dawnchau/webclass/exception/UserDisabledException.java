package com.dawnchau.webclass.exception;

import org.springframework.security.core.AuthenticationException;

public class UserDisabledException extends AuthenticationException {
    public UserDisabledException(String explanation) {
        super(explanation);
    }
}
