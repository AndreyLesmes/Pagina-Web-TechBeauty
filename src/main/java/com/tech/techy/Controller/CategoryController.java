package com.tech.techy.Controller;

import com.tech.techy.Business.CategoryBusiness;
import com.tech.techy.Dtos.CategoryDto;
import org.springframework.web.bind.annotation.RestController;

import com.tech.techy.Entity.Category;

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
@RequestMapping(path="/api/category", method= {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    private CategoryBusiness categoryBusiness;

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findAllCategories() {
        Map<String, Object> res = new HashMap<>();
        List<CategoryDto> listCategories = this.categoryBusiness.findAll();
        res.put("status", "success");
        res.put("data", listCategories);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<Map<String, Object>> getCategoryById(@PathVariable int id) {
        try {
            Category data = categoryBusiness.findById(id);
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
    public ResponseEntity<Map<String, Object>> createCategory(@RequestBody CategoryDto newCategory) {
        Map<String, Object> res = new HashMap<>();
        try {
            categoryBusiness.createCategory(newCategory);
            res.put("status", "success");
            res.put("data", newCategory);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateCategory(@PathVariable int id, @RequestBody CategoryDto existingCategory) {
        Map<String, Object> res = new HashMap<>();
        try {
            categoryBusiness.updateCategory(id, existingCategory);
            res.put("status", "success");
            res.put("data", existingCategory);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteCategory(@PathVariable int id) {
        Map<String, Object> res = new HashMap<>();
        try {
            categoryBusiness.deleteCategory(id);
            res.put("status", "success");
            res.put("message", "Category deleted successfully");
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
