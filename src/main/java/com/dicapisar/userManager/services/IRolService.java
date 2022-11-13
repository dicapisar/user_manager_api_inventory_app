package com.dicapisar.userManager.services;

import com.dicapisar.userManager.dtos.response.RolInfoResponse;

import java.util.List;

public interface IRolService {
    List<RolInfoResponse> getRolInfoResponseList();
}
