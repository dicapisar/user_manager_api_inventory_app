package com.dicapisar.userManager.services;

import com.dicapisar.userManager.exceptions.ActionNotAllowedException;
import com.dicapisar.userManager.exceptions.UserNotFoundException;
import com.dicapisar.userManager.models.User;
import com.dicapisar.userManager.repository.UserRepository;
import com.dicapisar.userManager.services.external.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static com.dicapisar.userManager.commons.UserManagerConstants.*;

@Service
@AllArgsConstructor
public class ChangeStatusUserService implements IChangeStatusUserService {
    private UserRepository userRepository;
    private IUserService userService;

    @Transactional
    public void changeStatusUserById(Long idUser, String status, Long idUserModifier)
            throws UserNotFoundException, ActionNotAllowedException {

        User userCreator = userService.getUserById(idUserModifier);
        User userModified = userService.getUserById(idUser);

        this.validateUserModified(userModified);

        this.changeStatusUser(userModified, userCreator, status);
        
        userRepository.save(userModified);
        
    }

    private void validateUserModified(User user) throws ActionNotAllowedException {
        if (user.getRol().getName().equals(ADMIN)) {
            throw new ActionNotAllowedException("Cannot change the status of an administrator user");
        }
    }
    
    private void changeStatusUser(User userToChange, User userModifier, String status) {
        switch (status) {
            case ACTIVATE:
                if (!userToChange.isActive()) {
                    userToChange.setActive(true);
                }
                break;
            case DEATIVATE:
                if (userToChange.isActive()) {
                    userToChange.setActive(false);
                }
                break;
        }
        
        userToChange.setUpdater(userModifier);
        userToChange.setUpdateDate(LocalDateTime.now());
    }
}
