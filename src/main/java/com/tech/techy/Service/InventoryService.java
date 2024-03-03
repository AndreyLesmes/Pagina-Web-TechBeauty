package com.tech.techy.Service;

import java.util.List;

import com.tech.techy.Entity.Inventory;

public interface InventoryService {
    List<Inventory> findAll();

    Inventory findById(int pkId);

    void create(Inventory inventory);

    void update(Inventory inventory);

    void delete(Inventory inventory);
}
