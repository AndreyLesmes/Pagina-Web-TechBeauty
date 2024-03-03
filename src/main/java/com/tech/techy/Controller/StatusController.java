package com.tech.techy.Controller;

import com.tech.techy.Business.StatusBusiness;
import com.tech.techy.Dtos.StatusDto;
import org.springframework.web.bind.annotation.RestController;

import com.tech.techy.Entity.Status;

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
@RequestMapping(path="/api/status", method= {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
@CrossOrigin("*")
public class StatusController {
    @Autowired
    private StatusBusiness statusBusiness;

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findAllStatuses() {
        Map<String, Object> res = new HashMap<>();
        List<StatusDto> listStatuses = this.statusBusiness.findAll();
        res.put("status", "success");
        res.put("data", listStatuses);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<Map<String, Object>> getStatusById(@PathVariable int id) {
        try {
            Status data = statusBusiness.findById(id);
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
    public ResponseEntity<Map<String, Object>> createStatus(@RequestBody StatusDto newStatus) {
        Map<String, Object> res = new HashMap<>();
        try {
            statusBusiness.createStatus(newStatus);
            res.put("status", "success");
            res.put("data", newStatus);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateStatus(@PathVariable int id, @RequestBody StatusDto existingStatus) {
        Map<String, Object> res = new HashMap<>();
        try {
            statusBusiness.updateStatus(id, existingStatus);
            res.put("status", "success");
            res.put("data", existingStatus);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteStatus(@PathVariable int id) {
        Map<String, Object> res = new HashMap<>();
        try {
            statusBusiness.deleteStatus(id);
            res.put("status", "success");
            res.put("message", "Status deleted successfully");
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
