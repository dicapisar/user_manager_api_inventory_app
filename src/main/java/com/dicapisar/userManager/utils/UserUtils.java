package com.dicapisar.userManager.utils;

import com.dicapisar.userManager.dtos.response.NewUserCreatedResponse;
import com.dicapisar.userManager.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserUtils {

    public static NewUserCreatedResponse toNewUserCreatedResponse(User user, String password){

        return new NewUserCreatedResponse(user.getId(), user.getName(), password,
                user.getRol().getId(), user.getRol().getName());
    }
}
