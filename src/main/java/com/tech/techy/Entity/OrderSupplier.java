package com.tech.techy.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order_suppliers")
@Data
public class OrderSupplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pkId")
    private int pkId;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "fkIdEmployees")
    private Employee fkIdEmployee;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "fkNitSuppliers")
    private Supplier fkNitSupplier;

    @Column(name = "productQuantity", length = 5)
    private String productQuantity;

    @Column(name = "dateOrder")
    private Date date;

    @Column(name = "price", length = 10)
    private int price;

    @Column(name = "typePay", length = 20)
    private String typePay;

    @Column(name = "totalOrder", length = 10)
    private int totalOrder;

    @Column(name = "totalIva", length = 10)
    private int totalIva;

    @JsonBackReference
    @ManyToMany(mappedBy = "orderSupplierList", fetch = FetchType.LAZY)
    private List<Product> productList;
}
