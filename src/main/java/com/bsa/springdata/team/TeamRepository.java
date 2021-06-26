package com.bsa.springdata.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TeamRepository extends JpaRepository<Team, UUID> {

    @Query("select t from Team t where t.technology.name = :technologyName")
    List<Team> findByTechnologyName(String technologyName);

    @Modifying
    @Query("update Team set name = concat(name, '_', project.name, '_', technology.name) where name = :name")
    void normalizeName(String name);
}

