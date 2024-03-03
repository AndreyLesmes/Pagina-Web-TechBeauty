package com.tech.techy.Business;

import com.tech.techy.Dtos.RoleDto;
import com.tech.techy.Dtos.UserDto;
import com.tech.techy.Entity.Role;
import com.tech.techy.Entity.User;
import com.tech.techy.Service.RoleService;
import com.tech.techy.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserBusiness {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    private List<User> userList;

    private List<UserDto> userDtoList = new ArrayList<>();

    public List<UserDto> findAll(){
        this.userList=this.userService.findAll();
        this.userList.stream().forEach(user -> {
            UserDto userDto = new UserDto();
            userDto.setPkId(user.getPkId());
            userDto.setName(user.getName());
            userDto.setLastName(user.getLastName());
            userDto.setTelephone(user.getTelephone());
            userDto.setAddress(user.getAddress());
            userDto.setEmail(user.getEmail());
            userDto.setPasswordU(user.getPasswordU());

            Role role = user.getNumRole();
            if(role != null) {
                RoleDto roleDto = new RoleDto();
                roleDto.setPkNumRoles(role.getPkNumRoles());
                roleDto.setName(role.getName());
                userDto.setNumRole(roleDto);
            }


            userDtoList.add(userDto);
        });
        return this.userDtoList;
    }

    public User findById(int id) {
        return this.userService.findById(id);
    }

    public void createUser(UserDto userDto) throws Exception {
        User user = new User();
        RoleDto roleDto = userDto.getNumRole();
        Role role = roleService.findById(roleDto.getPkNumRoles());

        if (role == null) {
            throw new Exception("Role not found with ID: " + roleDto.getPkNumRoles());
        }

        user.setNumRole(role);
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setTelephone(userDto.getTelephone());
        user.setAddress(userDto.getAddress());
        user.setEmail(userDto.getEmail());
        user.setPasswordU(userDto.getPasswordU());

        this.userService.create(user);

    }

    public void updateUser(int id, UserDto updatedUserDto) throws Exception {
        User existingUser = userService.findById(id);
        if(existingUser == null) {
            throw new Exception("Usuario no encontrado!");
        }
        existingUser.setName(updatedUserDto.getName());
        existingUser.setLastName(updatedUserDto.getLastName());
        existingUser.setTelephone(updatedUserDto.getTelephone());
        existingUser.setAddress(updatedUserDto.getAddress());
        existingUser.setEmail(updatedUserDto.getEmail());
        existingUser.setPasswordU(updatedUserDto.getPasswordU());

        if(updatedUserDto.getNumRole() != null) {
            int roleId = updatedUserDto.getNumRole().getPkNumRoles();
            Role role = roleService.findById(roleId);
            if (role == null) {
                throw new Exception("El id " + roleId + " no se encuentra dentro los roles validos!");
            }
            existingUser.setNumRole(role);
        }
        this.userService.update(existingUser);
    }

    public void deleteUser(int id) throws Exception{
        User existingUser = userService.findById(id);
        if (existingUser == null) {
            throw new Exception("Rol no encontrado!");
        }

        this.userService.delete(existingUser);
    }
}
