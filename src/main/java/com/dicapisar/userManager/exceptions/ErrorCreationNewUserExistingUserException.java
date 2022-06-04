package com.dicapisar.userManager.exceptions;

import org.springframework.http.HttpStatus;

public class ErrorCreationNewUserExistingUserException extends UserManagerException{

    public ErrorCreationNewUserExistingUserException(String userName, boolean isActive) {
        super("Error creating a new user, a User "+ (isActive ? "active" : "deactive") + "with the same userName already exists: '" + userName + "'", HttpStatus.BAD_REQUEST);
    }
}
