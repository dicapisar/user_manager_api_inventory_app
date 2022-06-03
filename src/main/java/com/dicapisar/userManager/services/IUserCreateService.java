package com.dicapisar.userManager.services;

import com.dicapisar.userManager.dtos.request.NewUserCreateRequest;
import com.dicapisar.userManager.dtos.response.NewUserCreatedResponse;
import com.dicapisar.userManager.exceptions.ErrorCreationNewUserExistingUser;

public interface IUserCreateService {
    NewUserCreatedResponse createNewUser(NewUserCreateRequest newUser, Long idUserCreator) throws ErrorCreationNewUserExistingUser;
}
