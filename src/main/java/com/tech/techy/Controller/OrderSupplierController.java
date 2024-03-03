package com.tech.techy.Controller;

import com.tech.techy.Business.OrderSupplierBusiness;
import com.tech.techy.Dtos.OrderSupplierDto;
import org.springframework.web.bind.annotation.RestController;

import com.tech.techy.Entity.OrderSupplier;

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
@RequestMapping(path="/api/order-supplier", method= {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
@CrossOrigin("*")
public class OrderSupplierController {
    @Autowired
    private OrderSupplierBusiness orderSupplierBusiness;

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findAllOrderSuppliers() {
        Map<String, Object> res = new HashMap<>();
        List<OrderSupplierDto> listOrderSuppliers = this.orderSupplierBusiness.findAll();
        res.put("status", "success");
        res.put("data", listOrderSuppliers);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<Map<String, Object>> getOrderSupplierById(@PathVariable int id) {
        try {
            OrderSupplier data = orderSupplierBusiness.findById(id);
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
    public ResponseEntity<Map<String, Object>> createOrderSupplier(@RequestBody OrderSupplierDto newOrderSupplier) {
        Map<String, Object> res = new HashMap<>();
        try {
            orderSupplierBusiness.createOrderSupplier(newOrderSupplier);
            res.put("status", "success");
            res.put("data", newOrderSupplier);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateOrderSupplier(@PathVariable int id, @RequestBody OrderSupplierDto existingOrderSupplier) {
        Map<String, Object> res = new HashMap<>();
        try {
            orderSupplierBusiness.updateOrderSupplier(id, existingOrderSupplier);
            res.put("status", "success");
            res.put("data", existingOrderSupplier);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteOrderSupplier(@PathVariable int id) {
        Map<String, Object> res = new HashMap<>();
        try {
            orderSupplierBusiness.deleteOrderSupplier(id);
            res.put("status", "success");
            res.put("message", "Order Supplier deleted successfully");
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}