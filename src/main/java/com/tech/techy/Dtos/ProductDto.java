package com.tech.techy.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private int pkId;

    private CategoryDto fkIdCategories;

    private String name;

    private int price;

    private int productQuantity;

    private String description;

    private String measurementUnit;

    private String productReference;
}
