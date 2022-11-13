package com.dicapisar.userManager.services;

import com.dicapisar.userManager.dtos.response.RolInfoResponse;
import com.dicapisar.userManager.models.Rol;
import com.dicapisar.userManager.repository.RolRepository;
import com.dicapisar.userManager.utils.RolUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RolService implements IRolService {
    private RolRepository rolRepository;

    public List<RolInfoResponse> getRolInfoResponseList() {
        List<Rol> rolList = rolRepository.getRolList();
        List<RolInfoResponse> rolInfoResponseList = new ArrayList<>();

        rolList.forEach(rol -> rolInfoResponseList.add(RolUtils.toRolInfoResponse(rol)));

        return rolInfoResponseList;
    }
}
