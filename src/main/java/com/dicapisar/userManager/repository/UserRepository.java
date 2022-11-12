package com.dicapisar.userManager.repository;

import com.dicapisar.userManager.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);
    User findUserByName(String name);
    @Query("select u from User u where u.isActive=true")
    List<User> getUsersListWithStatusActivateTrue();
}
