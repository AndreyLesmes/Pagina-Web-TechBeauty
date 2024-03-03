package com.tech.techy.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "suppliers")
@Data
public class Supplier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pkNit")
    private int pkNit;

    @Column(name = "name", length = 25)
    private String name;

    @Column(name = "lastName", length = 25)
    private String lastName;

    @Column(name = "address", length = 60)
    private String address;

    @Column(name = "telephone", length = 11)
    private double telephone;

    @Column(name = "email", length = 40)
    private String email;

    @Column(name = "companyName", length = 50)
    private String companyName;

    @Column(name = "productType", length = 30)
    private String productType;

    @Column(name = "supplierBrand", length = 30)
    private String supplierBrand;

    @JsonBackReference
    @OneToMany(mappedBy = "fkNitSupplier")
    private List<OrderSupplier> orderSuppliersList;
}
