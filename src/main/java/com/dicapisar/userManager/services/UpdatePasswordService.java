package com.dicapisar.userManager.services;

import com.dicapisar.userManager.dtos.request.ChangePasswordDTO;
import com.dicapisar.userManager.exceptions.UserNotFoundException;
import com.dicapisar.userManager.models.User;
import com.dicapisar.userManager.repository.UserRepository;
import com.dicapisar.userManager.services.external.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UpdatePasswordService implements IUpdatePasswordService {

    private IUserService userService;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Transactional
    public void updatePasswordByUserId(ChangePasswordDTO changePasswordDTO, Long userId, Long updaterId)
            throws UserNotFoundException {

        User user = userService.getUserByIdAndIsActive(userId);
        User updater = userService.getUserByIdAndIsActive(updaterId);

        this.updatePasswordOfUser(user, updater, changePasswordDTO.getNewPassword());

        userRepository.save(user);

    }

    private void updatePasswordOfUser(User user, User updater, String newPassword) {
        String passwordEncoded = passwordEncoder.encode(newPassword);

        user.setPassword(passwordEncoded);
        user.setUpdater(updater);
        user.setUpdateDate(LocalDateTime.now());
    }
}
