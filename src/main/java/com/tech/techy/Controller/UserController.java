package com.tech.techy.Controller;

import com.tech.techy.Business.UserBusiness;
import com.tech.techy.Dtos.UserDto;
import com.tech.techy.Entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/user", method = { RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST })
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserBusiness userBusiness;

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findAllUsers() {
        Map<String, Object> res = new HashMap<>();
        List<UserDto> listUsers = this.userBusiness.findAll();
        res.put("status", "success");
        res.put("data", listUsers);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable int id) {
        try {
            User data = userBusiness.findById(id);
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
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody UserDto newUser) {
        Map<String, Object> res = new HashMap<>();

        try {
            userBusiness.createUser(newUser);
            res.put("status", "Usuario creado!");
            res.put("data", newUser);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable int id, @RequestBody UserDto existingUser) {
        Map<String, Object> res = new HashMap<>();
        try {
            userBusiness.updateUser(id, existingUser);
            res.put("status", "success");
            res.put("data", existingUser);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

@DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable int id) {
        Map<String, Object> res = new HashMap<>();
        try {
            userBusiness.deleteUser(id);
            res.put("status", "success");
            res.put("message", "User deleted successfully");
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
