package com.dicapisar.userManager.services.external;

import com.dicapisar.userManager.exceptions.UserNotFoundException;
import com.dicapisar.userManager.models.User;

public interface IUserService {
    User getUserById(Long idUser) throws UserNotFoundException;
    User getUserByName(String nameUser) throws UserNotFoundException;
    User getUserByNameWhitOutValidate(String nameUser);

}
