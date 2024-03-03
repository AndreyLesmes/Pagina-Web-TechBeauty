package com.tech.techy.Controller;

import com.tech.techy.Business.CustomerBusiness;
import com.tech.techy.Dtos.CustomerDto;
import org.springframework.web.bind.annotation.RestController;

import com.tech.techy.Entity.Customer;

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
@RequestMapping(path="/api/customer", method= {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
@CrossOrigin("*")
public class CustomerController {
    @Autowired
    private CustomerBusiness customerBusiness;

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findAllCustomers() {
        Map<String, Object> res = new HashMap<>();
        List<CustomerDto> listCustomers = this.customerBusiness.findAll();
        res.put("status", "success");
        res.put("data", listCustomers);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<Map<String, Object>> getCustomerById(@PathVariable int id) {
        try {
            Customer data = customerBusiness.findById(id);
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
    public ResponseEntity<Map<String, Object>> createCustomer(@RequestBody CustomerDto newCustomer) {
        Map<String, Object> res = new HashMap<>();
        try {
            customerBusiness.createCustomer(newCustomer);
            res.put("status", "success");
            res.put("data", newCustomer);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateCustomer(@PathVariable int id, @RequestBody CustomerDto existingCustomer) {
        Map<String, Object> res = new HashMap<>();
        try {
            customerBusiness.updateCustomer(id, existingCustomer);
            res.put("status", "success");
            res.put("data", existingCustomer);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteCustomer(@PathVariable int id) {
        Map<String, Object> res = new HashMap<>();
        try {
            customerBusiness.deleteCustomer(id);
            res.put("status", "success");
            res.put("message", "Customer deleted successfully");
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

