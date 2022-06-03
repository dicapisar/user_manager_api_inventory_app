package com.dicapisar.userManager.services;

import com.dicapisar.userManager.dtos.request.NewUserCreateRequest;
import com.dicapisar.userManager.dtos.response.NewUserCreatedResponse;
import com.dicapisar.userManager.exceptions.ErrorCreationNewUserExistingUser;
import com.dicapisar.userManager.models.User;
import com.dicapisar.userManager.repository.RolRepository;
import com.dicapisar.userManager.repository.UserRepository;
import com.dicapisar.userManager.utils.PasswordGenerator;
import com.dicapisar.userManager.utils.UserUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserCreateService implements IUserCreateService{
    private UserRepository userRepository;
    private RolRepository rolRepository;
    private PasswordEncoder passwordEncoder;

    public NewUserCreatedResponse createNewUser(NewUserCreateRequest newUser, Long idUserCreator)
            throws ErrorCreationNewUserExistingUser {

        User creator = this.getUserById(idUserCreator);
        User user = this.getUserByName(newUser.getName());
        if (user != null) {
            if (!user.isActive()) {
                throw new ErrorCreationNewUserExistingUser(newUser.getName(), false);
            }
            throw new ErrorCreationNewUserExistingUser(newUser.getName(), true);
        }

        String password = PasswordGenerator.getPassword();

        User userCreated = userRepository.save(this.createNewUser(newUser, creator, password));

        return UserUtils.toNewUserCreatedResponse(userCreated, password);
    }

    private User getUserById(Long idUser) {
        return userRepository.findUserById(idUser);
    }

    private User getUserByName(String nameUser) {
        return userRepository.findUserByName(nameUser);
    }

    private User createNewUser(NewUserCreateRequest newUser, User userCreator, String password) {
        User user = new User();

        user.setCreator(userCreator);
        user.setActive(true);
        user.setCreationDate(LocalDateTime.now());
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
