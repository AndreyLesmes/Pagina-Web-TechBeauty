package com.tech.techy.Controller;

import com.tech.techy.Business.EmployeeBusiness;
import com.tech.techy.Dtos.EmployeeDto;
import org.springframework.web.bind.annotation.RestController;

import com.tech.techy.Entity.Employee;

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
@RequestMapping(path = "/api/employee", method = { RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST })
@CrossOrigin("*")
public class EmployeeController {
    @Autowired
    private EmployeeBusiness employeeBusiness;

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findAllEmployees() {
        Map<String, Object> res = new HashMap<>();
        List<EmployeeDto> listEmployees = this.employeeBusiness.findAll();
        res.put("status", "success");
        res.put("data", listEmployees);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<Map<String, Object>> getEmployeeById(@PathVariable int id) {
        try {
            Employee data = employeeBusiness.findById(id);
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
    public ResponseEntity<Map<String, Object>> createEmployee(@RequestBody EmployeeDto newEmployee) {
        Map<String, Object> res = new HashMap<>();
        try {
            employeeBusiness.createEmployee(newEmployee);
            res.put("status", "success");
            res.put("data", newEmployee);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateEmployee(@PathVariable int id,
            @RequestBody EmployeeDto existingEmployee) {
        Map<String, Object> res = new HashMap<>();
        try {
            employeeBusiness.updateEmployee(id, existingEmployee);
            res.put("status", "success");
            res.put("data", existingEmployee);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteEmployee(@PathVariable int id) {
        Map<String, Object> res = new HashMap<>();
        try {
            employeeBusiness.deleteEmployee(id);
            res.put("status", "success");
            res.put("message", "Employee deleted successfully");
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
