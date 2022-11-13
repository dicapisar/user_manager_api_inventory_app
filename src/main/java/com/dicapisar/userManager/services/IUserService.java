package com.dicapisar.userManager.services;

import com.dicapisar.userManager.dtos.response.UserInfoResponse;
import com.dicapisar.userManager.exceptions.UserNotFoundException;
import com.dicapisar.userManager.models.User;

import java.util.List;

public interface IUserService {
    User getUserById(Long idUser) throws UserNotFoundException;
    User getUserByName(String nameUser) throws UserNotFoundException;
    User getUserByNameWhitOutValidate(String nameUser);
    User getUserByIdAndIsActive(Long idUser) throws UserNotFoundException;
    List<UserInfoResponse> getUsersList();

}
