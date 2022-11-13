package com.dicapisar.userManager.utils;

import com.dicapisar.userManager.dtos.response.NewUserCreatedResponse;
import com.dicapisar.userManager.dtos.response.UserInfoResponse;
import com.dicapisar.userManager.models.User;

public class UserUtils {

    public static NewUserCreatedResponse toNewUserCreatedResponse(User user, String password){

        return new NewUserCreatedResponse(user.getId(), user.getName(), password,
                user.getRol().getId(), user.getRol().getName());
    }

    public static UserInfoResponse toUserInfoDTO(User user) {
        return new UserInfoResponse(
                user.getId(),
                user.getName(),
                user.getRol().getName(),
                user.getCreationDate(),
                user.getUpdateDate()
        );
    }
}
