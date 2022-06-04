package com.dicapisar.userManager.services;

import com.dicapisar.userManager.exceptions.ActionNotAllowedException;
import com.dicapisar.userManager.exceptions.UserNotFoundException;

public interface IChangeStatusUserService {
    void changeStatusUserById(Long idUser, String status, Long idUserModifier) throws UserNotFoundException, ActionNotAllowedException;
}
