package com.tech.techy.Controller;

import com.tech.techy.Business.ProductBusiness;
import com.tech.techy.Dtos.ProductDto;
import org.springframework.web.bind.annotation.RestController;

import com.tech.techy.Entity.Product;

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
@RequestMapping(path = "/api/product", method = { RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST })
@CrossOrigin("*")
public class ProductController {
    @Autowired
    private ProductBusiness productBusiness;

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findAllProducts() {
        Map<String, Object> res = new HashMap<>();
        List<ProductDto> listProducts = this.productBusiness.findAll();
        res.put("status", "success");
        res.put("data", listProducts);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<Map<String, Object>> getProductById(@PathVariable int id) {
        try {
            Product data = productBusiness.findById(id);
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
    public ResponseEntity<Map<String, Object>> createProduct(@RequestBody ProductDto newProduct) {
        Map<String, Object> res = new HashMap<>();
        try {
            productBusiness.createProduct(newProduct);
            res.put("status", "success");
            res.put("data", newProduct);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateProduct(@PathVariable int id,
            @RequestBody ProductDto existingProduct) {
        Map<String, Object> res = new HashMap<>();
        try {
            productBusiness.updateProduct(id, existingProduct);
            res.put("status", "success");
            res.put("data", existingProduct);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteProduct(@PathVariable int id) {
        Map<String, Object> res = new HashMap<>();
        try {
            productBusiness.deleteProduct(id);
            res.put("status", "success");
            res.put("message", "Product deleted successfully");
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}