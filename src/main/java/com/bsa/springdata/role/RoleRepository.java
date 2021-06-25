package com.bsa.springdata.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    @Modifying
    void deleteByCode(String code);
}
