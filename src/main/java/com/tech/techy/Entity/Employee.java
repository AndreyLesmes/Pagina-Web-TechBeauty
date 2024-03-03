package com.tech.techy.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "employees")
@Data
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pkId")
    private int pkId;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "fkIdUsers")
    private User fkIdUser;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "fkIdStatus")
    private Status fkIdStatus;

    @Column(name = "eps", length = 30)
    private String eps;

    @Column(name = "socialClass", length = 11)
    private int socialClass;

    @Column(name = "maritalStatus", length = 15)
    private String maritalStatus;

    @JsonBackReference
    @OneToMany(mappedBy = "fkIdEmployee")
    private List<OrderSupplier> orderSupplierList;
}
