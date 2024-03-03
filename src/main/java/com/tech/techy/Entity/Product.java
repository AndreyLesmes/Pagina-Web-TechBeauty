package com.tech.techy.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "products")
@Data
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pkId")
    private int pkId;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "fkIdCategories")
    private Category fkIdCategories;

    @Column(name= "name", length = 30)
    private String name;

    @Column(name= "price", length = 10)
    private int price;

    @Column(name= "productQuantity", length = 5)
    private int productQuantity;

    @Column(name= "description")
    private String description;

    @Column(name= "measurementUnit", length = 25)
    private String measurementUnit;

    @Column(name= "productReference", length = 10)
    private String productReference;

    @JsonBackReference
    @OneToMany(mappedBy = "fkIdProducts")
    private List<BuyDetail> buyDetailProductList;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "invetoried",
            joinColumns = @JoinColumn(name = "fkIdProduct", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "fkIdInventiry", nullable = false)
    )
    private List<Inventory> inventoryList;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "purchases_orders",
            joinColumns = @JoinColumn(name = "fkIdProducts", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "fkIdOrders", nullable = false)
    )
    private List<OrderSupplier> orderSupplierList;
}
