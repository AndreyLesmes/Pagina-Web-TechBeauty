package com.tech.techy.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "inventories")
@Data
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pkId")
    private int pkId;

    @Column(name= "entryDate")
    private Date entryDate; 

    @Column(name= "entryQuantity", length = 5)
    private int entryQuantity;

    @Column(name= "stockQuantity", length = 5)
    private int stockQuantity;

    @JsonBackReference
    @ManyToMany(mappedBy = "inventoryList", fetch = FetchType.LAZY)
    private List<Product> productList;
}
