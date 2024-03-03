package com.tech.techy.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "customers")
@Data
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pkId")
    private int pkId;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "fkIdUsers")
    private User fkIdUser;

    @Column(name= "typeCustomer", length = 25)
    private String typeCustomer;

    @JsonBackReference
    @OneToMany(mappedBy = "fkIdCustomer")
    private List<Buy> buys;
}
