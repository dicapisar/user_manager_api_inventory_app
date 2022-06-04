package com.dicapisar.userManager.services;

import com.dicapisar.userManager.dtos.request.NewUserCreateRequest;
import com.dicapisar.userManager.dtos.response.NewUserCreatedResponse;
import com.dicapisar.userManager.exceptions.ActionNotAllowedException;
import com.dicapisar.userManager.exceptions.ErrorCreationNewUserExistingUserException;
import com.dicapisar.userManager.exceptions.UserNotFoundException;

public interface IUserCreateService {
    NewUserCreatedResponse createNewUser(NewUserCreateRequest newUser, Long idUserCreator) throws ErrorCreationNewUserExistingUserException, UserNotFoundException, ActionNotAllowedException;
}
