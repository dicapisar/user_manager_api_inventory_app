package com.dicapisar.userManager.controllers;

import com.dicapisar.userManager.dtos.request.NewUserCreateRequest;
import com.dicapisar.userManager.dtos.response.NewUserCreatedResponse;
import com.dicapisar.userManager.exceptions.ErrorCreationNewUserExistingUser;
import com.dicapisar.userManager.exceptions.SessionErrorException;
import com.dicapisar.userManager.exceptions.SessionWithOutPermissionException;
import com.dicapisar.userManager.services.IUserCreateService;
import com.dicapisar.userManager.services.UserCreateService;
import com.dicapisar.userManager.utils.SessionUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static com.dicapisar.userManager.commons.UserManagerConstants.*;
import static com.dicapisar.userManager.utils.SessionUtils.validateSessionExist;
import static com.dicapisar.userManager.utils.SessionUtils.validateSessionHavePermissions;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class CreationUsersController {

    private IUserCreateService userCreateService;

    @PostMapping("/create")
    public ResponseEntity<NewUserCreatedResponse> createNewUser(@RequestBody @Valid NewUserCreateRequest newUser,
                                                                HttpSession session)
            throws SessionErrorException, SessionWithOutPermissionException, ErrorCreationNewUserExistingUser {

        ArrayList<String> rolesPermissions = new ArrayList<>(List.of(ADMIN, MANAGER));

        validateSessionExist(session);
        validateSessionHavePermissions(session, rolesPermissions);

        return new ResponseEntity<>(userCreateService.createNewUser(newUser, SessionUtils.getIdUserSession(session)),
                HttpStatus.CREATED);
    }
}
