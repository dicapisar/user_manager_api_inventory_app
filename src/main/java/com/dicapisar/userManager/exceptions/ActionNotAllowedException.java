package com.dicapisar.userManager.exceptions;

import org.springframework.http.HttpStatus;

public class ActionNotAllowedException extends UserManagerException{

    public ActionNotAllowedException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }

}
