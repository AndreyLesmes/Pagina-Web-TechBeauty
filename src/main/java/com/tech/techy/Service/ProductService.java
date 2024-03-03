package com.tech.techy.Service;

import java.util.List;

import com.tech.techy.Entity.Product;

public interface ProductService {
    List<Product> findAll();

    Product findById(int pkId);

    void create(Product product);

    void update(Product product);

    void delete(Product product);
}
