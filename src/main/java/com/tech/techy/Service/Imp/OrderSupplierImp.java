package com.tech.techy.Service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.techy.Entity.OrderSupplier;
import com.tech.techy.Repository.OrderSupplierRepository;
import com.tech.techy.Service.OrderSupplierService;

@Service
public class OrderSupplierImp implements OrderSupplierService {

    @Autowired
    private OrderSupplierRepository orderSupplierRepository;

    @Override
    public List<OrderSupplier> findAll() {
        return this.orderSupplierRepository.findAll();
    }

    @Override
    public OrderSupplier findById(int pkId) {
        OrderSupplier orderSupplier = this.orderSupplierRepository.findById(pkId);
        return orderSupplier;
    }

    @Override
    public void create(OrderSupplier orderSupplier) {
        this.orderSupplierRepository.save(orderSupplier);
    }

    @Override
    public void update(OrderSupplier orderSupplier) {
        this.orderSupplierRepository.save(orderSupplier);
    }

    @Override
    public void delete(OrderSupplier orderSupplier) {
        this.orderSupplierRepository.delete(orderSupplier);
    }
}
