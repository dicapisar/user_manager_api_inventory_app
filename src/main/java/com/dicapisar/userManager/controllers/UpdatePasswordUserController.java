package com.dicapisar.userManager.controllers;

import com.dicapisar.userManager.dtos.request.ChangePasswordDTO;
import com.dicapisar.userManager.exceptions.SessionErrorException;
import com.dicapisar.userManager.exceptions.SessionWithOutPermissionException;
import com.dicapisar.userManager.exceptions.UserNotFoundException;
import com.dicapisar.userManager.services.IUpdatePasswordService;
import com.dicapisar.userManager.utils.SessionUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static com.dicapisar.userManager.commons.UserManagerConstants.*;
import static com.dicapisar.userManager.utils.SessionUtils.validateSessionExist;
import static com.dicapisar.userManager.utils.SessionUtils.validateSessionHavePermissions;

@RestController
@AllArgsConstructor
@RequestMapping("/users/update_password")
public class UpdatePasswordUserController {

    private IUpdatePasswordService updatePasswordService;

    @PostMapping()
    public ResponseEntity<?> changePassword(@RequestBody @Valid ChangePasswordDTO changePasswordDTO, HttpSession session)
            throws SessionErrorException, SessionWithOutPermissionException, UserNotFoundException {

        ArrayList<String> rolesPermissions = new ArrayList<>(List.of(ADMIN, MANAGER, EMPLOYED));

        validateSessionExist(session);
        validateSessionHavePermissions(session, rolesPermissions);

        Long idUser = SessionUtils.getIdUserSession(session);

        updatePasswordService.updatePasswordByUserId(changePasswordDTO, idUser, idUser);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{idUser}")
    public ResponseEntity<?> changePasswordByUserId(@RequestBody @Valid ChangePasswordDTO changePasswordDTO,
                                                    @PathVariable Long idUser, HttpSession session)
            throws SessionErrorException, SessionWithOutPermissionException, UserNotFoundException {

        ArrayList<String> rolesPermissions = new ArrayList<>(List.of(ADMIN));

        validateSessionExist(session);
        validateSessionHavePermissions(session, rolesPermissions);

        Long idUserUpdater = SessionUtils.getIdUserSession(session);

        updatePasswordService.updatePasswordByUserId(changePasswordDTO, idUser, idUserUpdater);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
