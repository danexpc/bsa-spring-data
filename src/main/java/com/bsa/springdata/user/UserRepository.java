package com.bsa.springdata.user;

import com.bsa.springdata.user.dto.UserDto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Modifying
    int deleteByExperience(int experience);

    @Query("select u from User u where u.team.room = :room and u.office.city = :city")
    List<User> findByRoomAndCity(String room, String city, Sort lastname);

    List<User>  findByExperience(int exp, Sort experience);

    @Query("select u from User u where u.office.city = :city")
    List<User> findByCity(String city, Sort lastname);
}
