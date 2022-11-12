package com.dicapisar.userManager.services;

import com.dicapisar.userManager.dtos.request.NewUserCreateRequest;
import com.dicapisar.userManager.dtos.response.NewUserCreatedResponse;
import com.dicapisar.userManager.exceptions.ActionNotAllowedException;
import com.dicapisar.userManager.exceptions.ErrorCreationNewUserExistingUserException;
import com.dicapisar.userManager.exceptions.UserNotFoundException;
import com.dicapisar.userManager.models.User;
import com.dicapisar.userManager.repository.RolRepository;
import com.dicapisar.userManager.repository.UserRepository;
import com.dicapisar.userManager.utils.PasswordGenerator;
import com.dicapisar.userManager.utils.UserUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static com.dicapisar.userManager.commons.UserManagerConstants.ADMIN;

@Service
@AllArgsConstructor
public class UserCreateService implements IUserCreateService{
    private UserRepository userRepository;
    private RolRepository rolRepository;
    private IUserService userService;
    private PasswordEncoder passwordEncoder;

    @Transactional
    public NewUserCreatedResponse createNewUser(NewUserCreateRequest newUser, Long idUserCreator)
            throws ErrorCreationNewUserExistingUserException, UserNotFoundException, ActionNotAllowedException {

        User creator = userService.getUserById(idUserCreator);

        this.validateCreationUser(creator, newUser);

        String password = PasswordGenerator.getPassword();

        User userCreated = userRepository.save(this.createNewUser(newUser, creator, password));

        return UserUtils.toNewUserCreatedResponse(userCreated, password);
    }

    private void validateCreationUser(User userCreator, NewUserCreateRequest newUser)
            throws ErrorCreationNewUserExistingUserException, ActionNotAllowedException {

        User user = userService.getUserByNameWhitOutValidate(newUser.getName());

        if (newUser.getRol() == 1 && !userCreator.getRol().getName().equals(ADMIN)) {
            throw new ActionNotAllowedException("Only administrator users can create other administrator users.");
        }

        if (user != null) {
            if (!user.isActive()) {
                throw new ErrorCreationNewUserExistingUserException(newUser.getName(), false);
            }
            throw new ErrorCreationNewUserExistingUserException(newUser.getName(), true);
        }
    }

    private User createNewUser(NewUserCreateRequest newUser, User userCreator, String password) {
        User user = new User();

        user.setCreator(userCreator);
        user.setActive(true);
        user.setCreationDate(LocalDateTime.now());
        user.setUpdateDate(LocalDateTime.now());
        user.setName(newUser.getName());
        user.setPassword(this.generateNewPassword(password));
        user.setRol(rolRepository.findRolById(newUser.getRol()));
        user.setUpdater(userCreator);

        return user;
    }

    private String generateNewPassword(String password) {
        return passwordEncoder.encode(password);
    }

}
