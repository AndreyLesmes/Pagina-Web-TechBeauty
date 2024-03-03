package com.tech.techy.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "roles")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pkNumRoles")
    private int pkNumRoles;

    @Column(name= "name", length = 25)
    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "numRole")
    private List<User> usersList;
}
