package com.tech.techy.Business;

import com.tech.techy.Dtos.CategoryDto;
import com.tech.techy.Entity.Category;
import com.tech.techy.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryBusiness {

    @Autowired
    private CategoryService categoryService;

    private List<Category> categoryList;

    private List<CategoryDto> categoryDtoList = new ArrayList<>();

    public List<CategoryDto> findAll() {
        this.categoryList = this.categoryService.findAll();
        this.categoryList.stream().forEach(category -> {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setPkId(category.getPkId());
            categoryDto.setName(category.getName());

            categoryDtoList.add(categoryDto);
        });
        return this.categoryDtoList;
    }

    public Category findById(int id) {
        return this.categoryService.findById(id);
    }

    public void createCategory(CategoryDto categoryDto) throws Exception {
        Category category = new Category();
        category.setName(categoryDto.getName());

        this.categoryService.create(category);
    }

    public void updateCategory(int id, CategoryDto updatedCategoryDto) throws Exception {
        Category existingCategory = categoryService.findById(id);
        if(existingCategory == null) {
            throw new Exception("Categoria no encontrada!");
        }
        existingCategory.setName(updatedCategoryDto.getName());

        this.categoryService.update(existingCategory);
    }

    public void deleteCategory(int id) throws Exception{
        Category existingCategory = categoryService.findById(id);
        if (existingCategory == null) {
            throw new Exception("Categoria no encontrada!");
        }

        this.categoryService.delete(existingCategory);
    }
}
