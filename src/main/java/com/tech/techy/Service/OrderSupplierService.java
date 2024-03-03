package com.tech.techy.Service;

import java.util.List;

import com.tech.techy.Entity.OrderSupplier;

public interface OrderSupplierService {
    List<OrderSupplier> findAll();

    OrderSupplier findById(int pkId);

    void create(OrderSupplier orderSupplier);

    void update(OrderSupplier orderSupplier);

    void delete(OrderSupplier orderSupplier);
}
