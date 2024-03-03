package com.tech.techy.Service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.techy.Entity.Supplier;
import com.tech.techy.Repository.SupplierRepository;
import com.tech.techy.Service.SupplierService;

@Service
public class SupplierImp implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public List<Supplier> findAll() {
        return this.supplierRepository.findAll();
    }

    @Override
    public Supplier findById(int pkNit) {
        Supplier supplier = this.supplierRepository.findById(pkNit);
        return supplier;
    }

    @Override
    public void create(Supplier supplier) {
        this.supplierRepository.save(supplier);
    }

    @Override
    public void update(Supplier supplier) {
        this.supplierRepository.save(supplier);
    }

    @Override
    public void delete(Supplier supplier) {
        this.supplierRepository.delete(supplier);
    }
}
