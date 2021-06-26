package com.bsa.springdata.office;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OfficeRepository extends JpaRepository<Office, UUID> {

    @Query("select distinct o from Office o " +
            "join o.users u " +
            "join u.team t " +
            "join t.technology techno " +
            "where techno.name = :technology")
    List<Office> getByTechnology(String technology);

    Optional<Office> getByAddress(String address);

    @Modifying
    @Query("update Office o " +
            "set o.address = :newAddress " +
            "where size(o.users) <> 0 and o.address = :oldAddress")
    void updateAddressIfAllUsersInProject(String oldAddress, String newAddress);

}
