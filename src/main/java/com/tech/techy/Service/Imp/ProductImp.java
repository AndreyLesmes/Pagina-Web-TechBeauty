package com.tech.techy.Service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.techy.Entity.Product;
import com.tech.techy.Repository.ProductRepository;
import com.tech.techy.Service.ProductService;

@Service
public class ProductImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product findById(int pkId) {
        Product product = this.productRepository.findById(pkId);
        return product;
    }

    @Override
    public void create(Product product) {
        this.productRepository.save(product);
    }

    @Override
    public void update(Product product) {
        this.productRepository.save(product);
    }

    @Override
    public void delete(Product product) {
        this.productRepository.delete(product);
    }
}
