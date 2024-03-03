package com.tech.techy.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "buys")
@Data
public class Buy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pkId")
    private int pkId;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "fkIdCustomer")
    private Customer fkIdCustomer;

    @Column(name= "productQuantity", length = 4)
    private int productQuantity;

    @Column(name= "dateBuy")
    private Date dateBuy;

    @Column(name= "subTotal", length = 10)
    private int subTotal;

    @Column(name= "totalIva", length = 10)
    private int totalIva;

    @Column(name= "total", length = 10)
    private int total;

    @JsonBackReference
    @OneToMany(mappedBy = "fkIdBuys")
    private List<BuyDetail> buyDetailList;
}
