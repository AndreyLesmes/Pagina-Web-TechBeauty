package com.tech.techy.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private int pkId;

    private UserDto fkIdUser;

    private String typeCustomer;
}
