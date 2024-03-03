package com.tech.techy.Business;

import com.tech.techy.Dtos.*;
import com.tech.techy.Entity.*;
import com.tech.techy.Service.CategoryService;
import com.tech.techy.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductBusiness {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    private List<Product> productList;

    private List<ProductDto> productDtoList = new ArrayList<>();

    public List<ProductDto> findAll(){
        this.productList = this.productService.findAll();
        this.productList.stream().forEach(product -> {
            ProductDto productDto = new ProductDto();
            productDto.setPkId(product.getPkId());

            Category category = product.getFkIdCategories();
            if (category != null) {
                CategoryDto categoryDto = new CategoryDto();
                categoryDto.setPkId(category.getPkId());
                categoryDto.setName(category.getName());
                productDto.setFkIdCategories(categoryDto);
            }

            productDto.setName(product.getName());
            productDto.setPrice(product.getPrice());
            productDto.setProductQuantity(product.getProductQuantity());
            productDto.setDescription(product.getDescription());
            productDto.setMeasurementUnit(product.getMeasurementUnit());
            productDto.setProductReference(product.getProductReference());

            productDtoList.add(productDto);
        });
        return this.productDtoList;
    }

    public Product findById(int id) {
        return this.productService.findById(id);
    }

    public void createProduct(ProductDto productDto) throws Exception {
        Product product = new Product();
        CategoryDto categoryDto = productDto.getFkIdCategories();
        Category category = categoryService.findById(categoryDto.getPkId());

        if (category == null) {
            throw new Exception("Role not found with ID: " + categoryDto.getPkId());
        }

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setProductQuantity(productDto.getProductQuantity());
        product.setDescription(productDto.getDescription());
        product.setMeasurementUnit(productDto.getMeasurementUnit());
        product.setProductReference(productDto.getProductReference());

        this.productService.create(product);

    }

    public void updateProduct(int id, ProductDto updatedProductDto) throws Exception {
        Product existingProduct = productService.findById(id);
        if(existingProduct == null) {
            throw new Exception("Producto no encontrado!");
        }

        if(updatedProductDto.getFkIdCategories() != null) {
            int categoryId = updatedProductDto.getFkIdCategories().getPkId();
            Category category = categoryService.findById(categoryId);
            if (category == null) {
                throw new Exception("El id " + categoryId + " no se encuentra!");
            }
            existingProduct.setFkIdCategories(category);
        }

        existingProduct.setName(updatedProductDto.getName());
        existingProduct.setPrice(updatedProductDto.getPrice());
        existingProduct.setProductQuantity(updatedProductDto.getProductQuantity());
        existingProduct.setDescription(updatedProductDto.getDescription());
        existingProduct.setMeasurementUnit(updatedProductDto.getMeasurementUnit());
        existingProduct.setProductReference(updatedProductDto.getProductReference());

        this.productService.update(existingProduct);
    }

    public void deleteProduct(int id) throws Exception{
        Product existingProduct = productService.findById(id);
        if (existingProduct == null) {
            throw new Exception("Producto no encontrado!");
        }

        this.productService.delete(existingProduct);
    }
}
