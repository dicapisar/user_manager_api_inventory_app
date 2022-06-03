package com.dicapisar.userManager.repository;

import com.dicapisar.userManager.models.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    Rol findRolById(Long rolId);
}
