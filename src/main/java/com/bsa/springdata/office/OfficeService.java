package com.bsa.springdata.office;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfficeService {
    @Autowired
    private OfficeRepository officeRepository;

    public List<OfficeDto> getByTechnology(String technology) {
        return officeRepository.getByTechnology(technology)
                .stream()
                .map(OfficeDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<OfficeDto> updateAddress(String oldAddress, String newAddress) {
        officeRepository.updateAddressIfAllUsersInProject(oldAddress, newAddress);
        return officeRepository
                .getByAddress(newAddress)
                .map(OfficeDto::fromEntity);
    }
}
