package com.tech.techy.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private int pkId;

    private UserDto fkIdUser;

    private StatusDto fkIdStatus;

    private String eps;

    private int socialClass;

    private String maritalStatus;
}
