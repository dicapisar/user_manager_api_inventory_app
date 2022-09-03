package com.dicapisar.userManager.services;

import com.dicapisar.userManager.dtos.request.ChangePasswordDTO;
import com.dicapisar.userManager.exceptions.UserNotFoundException;

public interface IUpdatePasswordService {

    void updatePasswordByUserId(ChangePasswordDTO changePasswordDTO, Long userId, Long updaterId) throws UserNotFoundException;

}
