package com.bsa.springdata.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    @Modifying
    @Query(
            value = "delete from roles r " +
                    "where r.code = :code and r.id not in " +
                    "(select role_id from user2role)",
            nativeQuery = true)
    void deleteByCodeWhichNotMatchesAnyUsers(String code);
}
