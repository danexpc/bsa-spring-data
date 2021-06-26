package com.bsa.springdata.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {

    @Query(value = "select count( distinct p) from projects p " +
                    "join teams t on p.id = t.project_id " +
                    "join users u on t.id = u.team_id " +
                    "join user2role u2r on u.id = u2r.user_id ", nativeQuery = true)
    int getCountWithRole(String role);
}