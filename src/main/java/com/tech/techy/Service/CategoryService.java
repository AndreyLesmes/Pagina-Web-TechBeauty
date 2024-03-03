package com.tech.techy.Service;

import java.util.List;

import com.tech.techy.Entity.Category;

public interface CategoryService {
    List<Category> findAll();

    Category findById(int pkId);

    void create(Category category);

    void update(Category category);

    void delete(Category category);
}
