package com.tech.techy.Service;

import java.util.List;

import com.tech.techy.Entity.Supplier;

public interface SupplierService {
    List<Supplier> findAll();

    Supplier findById(int pkNit);

    void create(Supplier supplier);

    void update(Supplier supplier);

    void delete(Supplier supplier);
}
