package com.tech.techy.Dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDto {
    private int pkId;

    private Date entryDate; 

    private int entryQuantity;

    private int stockQuantity;
}
