package com.tech.techy.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pkId")
    private int pkId;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "fkNumRoles")
    private Role numRole;

    @Column(name= "name", length = 25)
    private String name;

    @Column(name= "lastName", length = 25)
    private String lastName;

    @Column(name= "telephone", length = 10)
    private Long telephone;

    @Column(name= "address", length = 60)
    private String address;

    @Column(name= "email", length = 40)
    private String email;
    
    @Column(name= "passwordU", length = 16)
    private String passwordU;

    @JsonBackReference
    @OneToMany(mappedBy = "fkIdUser")
    private List<Customer> customersList;

    @JsonBackReference
    @OneToMany(mappedBy = "fkIdUser")
    private List<Employee> employeesList;
}
