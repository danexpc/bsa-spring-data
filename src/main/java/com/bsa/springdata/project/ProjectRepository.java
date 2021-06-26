package com.bsa.springdata.project;

import com.bsa.springdata.project.dto.ProjectSummaryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {

    @Query(value = "select count( distinct p) from projects p " +
            "join teams t on p.id = t.project_id " +
            "join users u on t.id = u.team_id " +
            "join user2role u2r on u.id = u2r.user_id ", nativeQuery = true)
    int getCountWithRole(String role);


    @Query(value = "select p.name, count(distinct t) as teamsNumber, count(distinct u) as developersNumber, " +
            "string_agg(distinct techno.name, ',' order by techno.name desc) as technologies from projects p " +
            "join teams t on p.id = t.project_id " +
            "join users u on t.id = u.team_id " +
            "join technologies techno on t.technology_id = techno.id " +
            "group by p.name " +
            "order by p.name", nativeQuery = true)
    List<ProjectSummaryDto> getSummary();
}