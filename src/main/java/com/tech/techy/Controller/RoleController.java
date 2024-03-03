package com.tech.techy.Controller;

import com.tech.techy.Business.RoleBusiness;
import com.tech.techy.Dtos.RoleDto;
import org.springframework.web.bind.annotation.RestController;

import com.tech.techy.Entity.Role;

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
@RequestMapping(path="/api/role", method= {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
@CrossOrigin("*")
public class RoleController {
    @Autowired
    private RoleBusiness roleBusiness;

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findAllRoles() {
        Map<String, Object> res = new HashMap<>();
        List<RoleDto> listRoles = this.roleBusiness.findAll();
        res.put("status", "success");
        res.put("data", listRoles);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<Map<String, Object>> getRoleById(@PathVariable int id) {
        try {
            Role data = roleBusiness.findById(id);
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
    public ResponseEntity<Map<String, Object>> createRole(@RequestBody RoleDto newRole) {
        Map<String, Object> res = new HashMap<>();
        try {
            roleBusiness.createRole(newRole);
            res.put("status", "success");
            res.put("data", newRole);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateRole(@PathVariable int id, @RequestBody RoleDto existingRole) {
        Map<String, Object> res = new HashMap<>();
        try {
            roleBusiness.updateRole(id, existingRole);
            res.put("status", "success");
            res.put("data", existingRole);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteRole(@PathVariable int id) {
        Map<String, Object> res = new HashMap<>();
        try {
            roleBusiness.deleteRole(id);
            res.put("status", "success");
            res.put("message", "Role deleted successfully");
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
