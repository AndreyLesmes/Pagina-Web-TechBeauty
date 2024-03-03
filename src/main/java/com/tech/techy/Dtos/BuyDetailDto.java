package com.tech.techy.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyDetailDto {
    private int pkId;

    private BuyDto fkIdBuys;

    private ProductDto fkIdProducts;

    private int productQuantity;
}
