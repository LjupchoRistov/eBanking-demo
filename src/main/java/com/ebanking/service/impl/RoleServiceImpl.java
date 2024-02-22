package com.ebanking.service.impl;

import com.ebanking.models.Role;
import com.ebanking.repository.RoleRepository;
import com.ebanking.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return this.roleRepository.findAll();
    }

    @Override
    public Role findByName(String name) {
        return this.roleRepository.findByName(name);
    }

    @Override
    public List<Role> removeRole(Long roleId, List<Role> roles) {
        roles.removeIf(role -> role.getId().equals(roleId));
        this.roleRepository.saveAll(roles);

        return roles;
    }
}
