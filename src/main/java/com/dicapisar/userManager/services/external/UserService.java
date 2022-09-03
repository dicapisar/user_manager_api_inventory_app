package com.dicapisar.userManager.services.external;

import com.dicapisar.userManager.exceptions.UserNotFoundException;
import com.dicapisar.userManager.models.User;
import com.dicapisar.userManager.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private UserRepository userRepository;

    public User getUserById(Long idUser) throws UserNotFoundException {
        User user = userRepository.findUserById(idUser);
        if (user == null) {
            throw new UserNotFoundException(generateMessage("id", idUser.toString()));
        }
        return user;
    }

    public User getUserByIdAndIsActive(Long idUser) throws UserNotFoundException {
        User user = userRepository.findUserById(idUser);
        if (user == null || !user.isActive()) {
            throw new UserNotFoundException(generateMessage("id", idUser.toString()));
        }
        return user;
    }

    public User getUserByName(String nameUser) throws UserNotFoundException {
        User user = userRepository.findUserByName(nameUser);
        if (user == null) {
            throw new UserNotFoundException(generateMessage("name", nameUser));
        }
        return user;
    }

    public User getUserByNameWhitOutValidate(String nameUser) {
        return userRepository.findUserByName(nameUser);
    }

    private String generateMessage(String fieldName, String value) {
       return "User not found with " + fieldName + " '" + value + "'";
    }

}
