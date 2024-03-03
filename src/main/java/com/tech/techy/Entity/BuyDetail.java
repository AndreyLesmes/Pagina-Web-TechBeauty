package com.tech.techy.Entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "buys_details")
@Data
public class BuyDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pkId")
    private int pkId;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "fkIdBuys")
    private Buy fkIdBuys;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "fkIdProducts")
    private Product fkIdProducts;

    @Column(name= "productQuantity", length = 4)
    private int productQuantity;
}
