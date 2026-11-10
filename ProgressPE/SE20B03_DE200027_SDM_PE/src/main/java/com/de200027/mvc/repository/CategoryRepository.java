package com.de200027.mvc.repository;

import com.de200027.mvc.entity.Category;

import java.util.List;

public interface CategoryRepository {
    public default List<Category> findAll() {
        return List.of();
    }
}

