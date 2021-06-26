package com.bsa.springdata.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeamRepository extends JpaRepository<Team, UUID> {

    Optional<Team> findByName(String name);

    @Query("select count(t) from Team t where t.technology.name = :name")
    long countByTechnologyName(String name);

    @Query("select t from Team t where t.technology.name = :technologyName")
    List<Team> findByTechnologyName(String technologyName);

    @Modifying
    @Query(
            value = "UPDATE teams t " +
                    "SET name = concat(t.name, '_', p.name, '_', techno.name) " +
                    "FROM projects p, technologies techno " +
                    "WHERE t.name = :name AND t.project_id = p.id AND t.technology_id = techno.id",
            nativeQuery = true
    )
    void normalizeName(String name);


}

