package com.bsa.springdata.user;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("select u " +
            "from User u " +
            "where u.team.room = :room and u.office.city = :city")
    List<User> findByRoomAndCity(String room, String city, Sort sort);

    List<User> findByExperienceGreaterThanEqual(int experience, Sort sort);

    @Query("select u " +
            "from User u " +
            "where u.office.city = :city")
    List<User> findByCity(String city, Sort sort);

    List<User> findByLastNameStartingWithIgnoreCase(String lastName, Pageable pageable);

    @Modifying
    int deleteByExperienceLessThan(int experience);
}
