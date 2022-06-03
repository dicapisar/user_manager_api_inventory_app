package com.dicapisar.userManager.exceptions;

import org.springframework.http.HttpStatus;

public class SessionErrorException extends UserManagerException{

    public SessionErrorException() {
        super("Session error, try to login again.", HttpStatus.FORBIDDEN);
    }
}
