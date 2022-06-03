package com.dicapisar.userManager.exceptions;

import org.springframework.http.HttpStatus;

public class ErrorCreationNewUserExistingUser extends UserManagerException{

    public ErrorCreationNewUserExistingUser(String userName, boolean isActive) {
        super("Error creating a new user, a User "+ (isActive ? "active" : "deactive") + "with the same userName already exists: '" + userName + "'", HttpStatus.FORBIDDEN);
    }
}
