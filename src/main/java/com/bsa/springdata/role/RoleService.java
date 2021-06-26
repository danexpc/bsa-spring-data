package com.bsa.springdata.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public void deleteRole(String roleCode) {
        // TODO: Use a single query
        roleRepository.deleteByCodeWhichNotMatchesAnyUsers(roleCode);
    }
}
