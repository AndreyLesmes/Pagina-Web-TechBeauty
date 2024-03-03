package com.tech.techy.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private int pkId;

    private RoleDto numRole;

    private String name;

    private String lastName;

    private Long telephone;

    private String address;

    private String email;
    
    private String passwordU;
}
