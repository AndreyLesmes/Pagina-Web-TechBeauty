package com.tech.techy.Service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.techy.Entity.Inventory;
import com.tech.techy.Repository.InventoryRepository;
import com.tech.techy.Service.InventoryService;

@Service
public class InventoryImp implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public List<Inventory> findAll() {
        return this.inventoryRepository.findAll();
    }

    @Override
    public Inventory findById(int pkId) {
        Inventory inventory = this.inventoryRepository.findById(pkId);
        return inventory;
    }

    @Override
    public void create(Inventory inventory) {
        this.inventoryRepository.save(inventory);
    }

    @Override
    public void update(Inventory inventory) {
        this.inventoryRepository.save(inventory);
    }

    @Override
    public void delete(Inventory inventory) {
        this.inventoryRepository.delete(inventory);
    }
}
