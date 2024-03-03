package com.tech.techy.Dtos;


import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderSupplierDto {
    private int pkId;

    private EmployeeDto fkIdEmployee;

    private SupplierDto fkNitSupplier;

    private String productQuantity;

    private Date date;

    private int price;

    private String typePay;

    private int totalOrder;

    private int totalIva;
}
