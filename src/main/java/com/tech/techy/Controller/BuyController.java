package com.tech.techy.Controller;

import com.tech.techy.Business.BuyBusiness;
import com.tech.techy.Dtos.BuyDto;
import org.springframework.web.bind.annotation.RestController;

import com.tech.techy.Entity.Buy;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

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
@RequestMapping(path = "/api/buy", method = { RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST })
@CrossOrigin("*")
public class BuyController {
    @Autowired
    private BuyBusiness buyBusiness;

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findAllBuy() {
        Map<String, Object> res = new HashMap<>();
        List<BuyDto> listBuy = this.buyBusiness.findAll();
        res.put("status", "success");
        res.put("data", listBuy);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<Map<String, Object>> getBuyById(@PathVariable int id) {
        try {
            Buy data = buyBusiness.findById(id);
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
    public ResponseEntity<Map<String, Object>> createBuy(@RequestBody BuyDto newBuy) {
        Map<String, Object> res = new HashMap<>();

        try {
            buyBusiness.createBuy(newBuy);
            res.put("status", "success");
            res.put("data", newBuy);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateBuy(@PathVariable int id, @RequestBody BuyDto existingBuy) {
        Map<String, Object> res = new HashMap<>();
        try {
            buyBusiness.updateBuy(id, existingBuy);
            res.put("status", "success");
            res.put("data", existingBuy);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteBuy(@PathVariable int id) {
        Map<String, Object> res = new HashMap<>();
        try {
            buyBusiness.deleteBuy(id);
            res.put("status", "success");
            res.put("message", "Buy deleted successfully");
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
