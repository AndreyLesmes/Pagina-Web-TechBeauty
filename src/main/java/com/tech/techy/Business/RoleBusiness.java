package com.tech.techy.Business;

import com.tech.techy.Dtos.RoleDto;
import com.tech.techy.Entity.Role;
import com.tech.techy.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleBusiness {

    @Autowired
    private RoleService roleService;

    private List<Role> roleList;

    private List<RoleDto> roleDtoList = new ArrayList<>();

    public List<RoleDto> findAll() {
        this.roleList = this.roleService.findAll();
        this.roleList.stream().forEach(role -> {
            RoleDto roleDto = new RoleDto();
            roleDto.setPkNumRoles(role.getPkNumRoles());
            roleDto.setName(role.getName());

            roleDtoList.add(roleDto);
        });
        return this.roleDtoList;
    }

    public Role findById(int id) {
        return this.roleService.findById(id);
    }

    public void createRole(RoleDto roleDto) throws Exception {
        Role role = new Role();

        role.setName(roleDto.getName());

        this.roleService.create(role);
    }

    public void updateRole(int id, RoleDto updatedRoleDto) throws Exception {
        Role existingRole = roleService.findById(id);
        if (existingRole == null) {
            throw new Exception("Rol no encontrado!");
        }

        existingRole.setName(updatedRoleDto.getName());

        this.roleService.update(existingRole);
    }

    public void deleteRole(int id) throws Exception{
        Role existingRole = roleService.findById(id);
        if (existingRole == null) {
            throw new Exception("Rol no encontrado!");
        }

        this.roleService.delete(existingRole);
    }
}
