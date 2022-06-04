package com.dicapisar.userManager.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends UserManagerException{

    public UserNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

}
