package com.tech.techy.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "status")
@Data
public class Status implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pkId")
    private int pkId;

    @Column(name = "name", length = 30)
    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "fkIdStatus")
    private List<Employee> employeesStatusList;
}
