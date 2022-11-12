package com.dicapisar.userManager.services;

import com.dicapisar.userManager.dtos.response.UserInfoDTO;
import com.dicapisar.userManager.exceptions.UserNotFoundException;
import com.dicapisar.userManager.models.User;
import com.dicapisar.userManager.repository.UserRepository;
import com.dicapisar.userManager.utils.UserUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Transactional
    public List<UserInfoDTO> getUsersList() {
        List<User> userList = userRepository.getUsersListWithStatusActivateTrue();
        List<UserInfoDTO> userInfoDTOList = new ArrayList<>();

        userList.forEach(user -> userInfoDTOList.add(UserUtils.toUserInfoDTO(user)));

        return userInfoDTOList;
    }

    private String generateMessage(String fieldName, String value) {
       return "User not found with " + fieldName + " '" + value + "'";
    }

}
