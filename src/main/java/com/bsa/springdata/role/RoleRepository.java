package com.bsa.springdata.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    @Modifying
    @Query(
            value = "DELETE FROM roles r WHERE r.code = :code AND r.id NOT IN " +
                    "(SELECT role_id FROM user2role)",
            nativeQuery = true)
    void deleteByCodeWhichNotMatchesAnyUsers(String code);
}
