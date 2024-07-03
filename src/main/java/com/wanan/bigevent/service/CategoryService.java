package com.wanan.bigevent.service;

import com.wanan.bigevent.pojo.Category;

import java.util.List;

public interface CategoryService {

    void add(Category category);

    List<Category> list();

    Category findById(int id);

    void update(Category category);

    void deleteById(int id);
}
