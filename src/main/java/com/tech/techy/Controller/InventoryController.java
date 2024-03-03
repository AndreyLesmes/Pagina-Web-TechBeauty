package com.tech.techy.Controller;

import com.tech.techy.Business.InventoryBusiness;
import com.tech.techy.Dtos.InventoryDto;
import org.springframework.web.bind.annotation.RestController;

import com.tech.techy.Entity.Inventory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path="/api/inventory", method= {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
@CrossOrigin("*")
public class InventoryController {
    @Autowired
    private InventoryBusiness inventoryBusiness;

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findAllInventories() {
        Map<String, Object> res = new HashMap<>();
        List<InventoryDto> listInventories = this.inventoryBusiness.findAll();
        res.put("status", "success");
        res.put("data", listInventories);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<Map<String, Object>> getInventoryById(@PathVariable int id) {
        try {
            Inventory data = inventoryBusiness.findById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("data", data);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createInventory(@RequestBody InventoryDto newInventory) {
        Map<String, Object> res = new HashMap<>();
        try {
            inventoryBusiness.createInventory(newInventory);
            res.put("status", "success");
            res.put("data", newInventory);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateInventory(@PathVariable int id, @RequestBody InventoryDto existingInventory) {
        Map<String, Object> res = new HashMap<>();
        try {
            inventoryBusiness.updateInventory(id, existingInventory);
            res.put("status", "success");
            res.put("data", existingInventory);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteInventory(@PathVariable int id) {
        Map<String, Object> res = new HashMap<>();
        try {
            inventoryBusiness.deleteInventory(id);
            res.put("status", "success");
            res.put("message", "Inventory deleted successfully");
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}