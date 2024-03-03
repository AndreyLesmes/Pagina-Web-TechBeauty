package com.tech.techy.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyDto {
    private int pkId;

    private CustomerDto fkIdCustomer;

    private int productQuantity;

    private Date dateBuy;

    private int subTotal;

    private int totalIva;

    private int total;
}
