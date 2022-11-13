package com.dicapisar.userManager.utils;

import com.dicapisar.userManager.dtos.response.RolInfoResponse;
import com.dicapisar.userManager.models.Rol;

public class RolUtils {

    public static RolInfoResponse toRolInfoResponse(Rol rol) {
        return new RolInfoResponse(rol.getId(), rol.getName());
    }
}
