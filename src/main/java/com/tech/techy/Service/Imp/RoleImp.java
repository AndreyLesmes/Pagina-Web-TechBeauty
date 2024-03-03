package com.tech.techy.Service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.techy.Entity.Role;
import com.tech.techy.Repository.RoleRepository;
import com.tech.techy.Service.RoleService;

@Service
public class RoleImp implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll(){
        return this.roleRepository.findAll();
    }

    @Override
    public Role findById(int pkNumRoles) {
        Role role = this.roleRepository.findById(pkNumRoles);
        return role;
    }

    @Override
    public void create(Role role) {
        this.roleRepository.save(role);
    }

    @Override
    public void update(Role role) {
        this.roleRepository.save(role);
    }

    @Override
    public void delete(Role role) {
        this.roleRepository.delete(role);
    }
    
}
