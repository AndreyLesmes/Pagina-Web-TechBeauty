package com.tech.techy.Controller;

import com.tech.techy.Business.BuyDetailBusiness;
import com.tech.techy.Dtos.BuyDetailDto;
import org.springframework.web.bind.annotation.RestController;

import com.tech.techy.Entity.BuyDetail;

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
@RequestMapping(path = "/api/buyDetail", method = { RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST })
@CrossOrigin("*")
public class BuyDetailController {
    @Autowired
    private BuyDetailBusiness buyDetailBusiness;

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findAllBuyDetails() {
        Map<String, Object> res = new HashMap<>();
        List<BuyDetailDto> listBuyDetails = this.buyDetailBusiness.findAll();
        res.put("status", "success");
        res.put("data", listBuyDetails);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<Map<String, Object>> findBuyDetailById(@PathVariable int id) {
        try {
            BuyDetail data = buyDetailBusiness.findById(id);
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
    public ResponseEntity<Map<String, Object>> createBuyDetail(@RequestBody BuyDetailDto newBuyDetail) {
        Map<String, Object> res = new HashMap<>();
        try {
            buyDetailBusiness.createBuyDetail(newBuyDetail);
            res.put("status", "success");
            res.put("data", newBuyDetail);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateBuyDetail(@PathVariable int id,
            @RequestBody BuyDetailDto existingBuyDetail) {
        Map<String, Object> res = new HashMap<>();
        try {
            buyDetailBusiness.updateBuyDetail(id, existingBuyDetail);
            res.put("status", "success");
            res.put("data", existingBuyDetail);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteBuyDetails(@PathVariable int id) {
        Map<String, Object> res = new HashMap<>();
        try {
            buyDetailBusiness.deleteBuyDetail(id);
            res.put("status", "success");
            res.put("message", "Buy Detail deleted successfully");
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}