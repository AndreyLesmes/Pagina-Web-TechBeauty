package com.tech.techy.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "categories")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pkId")
    private int pkId;

    @Column(name= "name", length = 25)
    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "fkIdCategories")
    private List<Product> productsList;
}
