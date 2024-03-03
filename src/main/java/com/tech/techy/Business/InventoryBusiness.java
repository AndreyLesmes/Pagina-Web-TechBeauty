package com.tech.techy.Business;

import com.tech.techy.Dtos.InventoryDto;
import com.tech.techy.Entity.Inventory;
import com.tech.techy.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InventoryBusiness {

    @Autowired
    private InventoryService inventoryService;

    private List<Inventory> inventoryList;

    private List<InventoryDto> inventoryDtoList = new ArrayList<>();

    public List<InventoryDto> findAll(){
        this.inventoryList = this.inventoryService.findAll();
        this.inventoryList.stream().forEach(inventory -> {
            InventoryDto inventoryDto = new InventoryDto();
            inventoryDto.setPkId(inventory.getPkId());
            inventoryDto.setEntryDate(inventory.getEntryDate());
            inventoryDto.setEntryQuantity(inventory.getEntryQuantity());
            inventoryDto.setStockQuantity(inventory.getStockQuantity());

            inventoryDtoList.add(inventoryDto);
        });
        return this.inventoryDtoList;
    }

    public Inventory findById(int id) {
        return this.inventoryService.findById(id);
    }

    public void createInventory(InventoryDto inventoryDto) throws Exception {
        Inventory inventory = new Inventory();
        inventory.setEntryDate(inventoryDto.getEntryDate());
        inventory.setEntryQuantity(inventoryDto.getEntryQuantity());
        inventory.setStockQuantity(inventoryDto.getStockQuantity());

        this.inventoryService.create(inventory);

    }

    public void updateInventory(int id, InventoryDto updatedInventoryDto) throws Exception {
        Inventory existingInventory = inventoryService.findById(id);
        if(existingInventory == null) {
            throw new Exception("Inventario no encontrado!");
        }
        existingInventory.setEntryDate(updatedInventoryDto.getEntryDate());
        existingInventory.setEntryQuantity(updatedInventoryDto.getEntryQuantity());
        existingInventory.setStockQuantity(updatedInventoryDto.getStockQuantity());

        this.inventoryService.update(existingInventory);
    }

    public void deleteInventory(int id) throws Exception{
        Inventory existingInventory = inventoryService.findById(id);
        if (existingInventory == null) {
            throw new Exception("Invetario no encontrado!");
        }

        this.inventoryService.delete(existingInventory);
    }
}
