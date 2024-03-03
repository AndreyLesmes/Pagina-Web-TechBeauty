package com.tech.techy.Controller;

import com.tech.techy.Business.SupplierBusiness;
import com.tech.techy.Dtos.SupplierDto;
import org.springframework.web.bind.annotation.RestController;

import com.tech.techy.Entity.Supplier;

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
@RequestMapping(path="/api/supplier", method= {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
@CrossOrigin("*")
public class SupplierController {
    @Autowired
    private SupplierBusiness supplierBusiness;

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findAllSuppliers() {
        Map<String, Object> res = new HashMap<>();
        List<SupplierDto> listSuppliers = this.supplierBusiness.findAll();
        res.put("status", "success");
        res.put("data", listSuppliers);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<Map<String, Object>> getSupplierById(@PathVariable int id) {
        try {
            Supplier data = supplierBusiness.findById(id);
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
    public ResponseEntity<Map<String, Object>> createSupplier(@RequestBody SupplierDto newSupplier) {
        Map<String, Object> res = new HashMap<>();
        try {
            supplierBusiness.createSupplier(newSupplier);
            res.put("status", "success");
            res.put("data", newSupplier);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateSupplier(@PathVariable int id, @RequestBody SupplierDto existingSupplier) {
        Map<String, Object> res = new HashMap<>();
        try {
            supplierBusiness.updateSupplier(id, existingSupplier);
            res.put("status", "success");
            res.put("data", existingSupplier);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteSupplier(@PathVariable int id) {
        Map<String, Object> res = new HashMap<>();
        try {
            supplierBusiness.deleteSupplier(id);
            res.put("status", "success");
            res.put("message", "Supplier deleted successfully");
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
