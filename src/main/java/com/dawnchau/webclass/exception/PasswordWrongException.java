package com.dawnchau.webclass.exception;

import org.springframework.security.core.AuthenticationException;

public class PasswordWrongException extends AuthenticationException {
    public PasswordWrongException(String message) {
        super(message);
    }
}
