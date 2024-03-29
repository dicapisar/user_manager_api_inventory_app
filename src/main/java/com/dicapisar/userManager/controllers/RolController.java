package com.dicapisar.userManager.controllers;

import com.dicapisar.userManager.dtos.response.RolInfoResponse;
import com.dicapisar.userManager.exceptions.SessionErrorException;
import com.dicapisar.userManager.exceptions.SessionWithOutPermissionException;
import com.dicapisar.userManager.services.IRolService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static com.dicapisar.userManager.commons.UserManagerConstants.ADMIN;
import static com.dicapisar.userManager.utils.SessionUtils.validateSessionExist;
import static com.dicapisar.userManager.utils.SessionUtils.validateSessionHavePermissions;

@RestController
@AllArgsConstructor
@RequestMapping("/roles")
public class RolController {

    private IRolService rolService;

    @GetMapping("/list")
    public ResponseEntity<List<RolInfoResponse>> getUserList(HttpSession session) throws SessionErrorException, SessionWithOutPermissionException {
        ArrayList<String> rolesPermissions = new ArrayList<>(List.of(ADMIN));

        validateSessionExist(session);
        validateSessionHavePermissions(session, rolesPermissions);

        return new ResponseEntity<>(rolService.getRolInfoResponseList(), HttpStatus.OK);
    }
}
