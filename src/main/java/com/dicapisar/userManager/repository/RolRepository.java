package com.dicapisar.userManager.repository;

import com.dicapisar.userManager.models.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    Rol findRolById(Long rolId);

    @Query("select r from Rol r")
    List<Rol> getRolList();
}
