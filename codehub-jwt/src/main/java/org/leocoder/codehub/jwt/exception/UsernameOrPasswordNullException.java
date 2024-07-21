package org.leocoder.codehub.jwt.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-21 20:01
 * @description :  Username or password is null exception
 */
public class UsernameOrPasswordNullException extends AuthenticationException {
    public UsernameOrPasswordNullException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UsernameOrPasswordNullException(String msg) {
        super(msg);
    }
}
