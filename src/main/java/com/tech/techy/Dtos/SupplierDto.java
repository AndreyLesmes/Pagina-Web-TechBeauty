package com.tech.techy.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDto {
    private int pkNit;

    private String name;

    private String lastName;

    private String address;

    private double telephone;

    private String email;

    private String companyName;

    private String productType;

    private String supplierBrand;
}
