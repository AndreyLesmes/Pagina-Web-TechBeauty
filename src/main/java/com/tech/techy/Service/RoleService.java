package com.tech.techy.Service;

import java.util.List;

import com.tech.techy.Entity.Role;

public interface RoleService {
    List<Role> findAll();

    Role findById(int pkNumRoles);

    void create(Role role);

    void update(Role role);

    void delete(Role role);
}
