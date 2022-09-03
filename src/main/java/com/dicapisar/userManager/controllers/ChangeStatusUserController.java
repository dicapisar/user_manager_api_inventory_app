package com.dicapisar.userManager.controllers;

import com.dicapisar.userManager.exceptions.ActionNotAllowedException;
import com.dicapisar.userManager.exceptions.SessionErrorException;
import com.dicapisar.userManager.exceptions.SessionWithOutPermissionException;
import com.dicapisar.userManager.exceptions.UserNotFoundException;
import com.dicapisar.userManager.services.IChangeStatusUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static com.dicapisar.userManager.commons.UserManagerConstants.*;
import static com.dicapisar.userManager.utils.SessionUtils.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users/change_status")
public class ChangeStatusUserController {

    private IChangeStatusUserService changeStatusUserService;

    @PutMapping("/activate/{userId}")
    public ResponseEntity<?> activateUserById(@PathVariable Long userId, HttpSession session)
            throws SessionErrorException, SessionWithOutPermissionException, UserNotFoundException, ActionNotAllowedException {

        this.validatePermissions(session);

        changeStatusUserService.changeStatusUserById(userId, ACTIVATE, getIdUserSession(session));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/deactivate/{userId}")
    public ResponseEntity<?> deactivateUserById(@PathVariable Long userId, HttpSession session)
            throws SessionErrorException, SessionWithOutPermissionException, UserNotFoundException, ActionNotAllowedException {

        this.validatePermissions(session);

        changeStatusUserService.changeStatusUserById(userId, DEATIVATE, getIdUserSession(session));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void validatePermissions(HttpSession session)
            throws SessionErrorException, SessionWithOutPermissionException {

        ArrayList<String> rolesPermissions = new ArrayList<>(List.of(ADMIN, MANAGER));

        validateSessionExist(session);
        validateSessionHavePermissions(session, rolesPermissions);
    }
}
