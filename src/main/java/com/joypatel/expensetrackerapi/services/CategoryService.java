package com.joypatel.expensetrackerapi.services;

import com.joypatel.expensetrackerapi.domain.Category;

import java.util.List;

public interface CategoryService {

    List<Category> fetchAllCategories(Integer userId);

    Category fetchCategoryById(Integer userId, Integer categoryId);

    Category addCategory(Integer userId, String title, String description);

    void updateCategory(Integer userId, Integer categoryId, Category category);

    void removeCategoryWithAllTransactions(Integer userId, Integer categoryId);

}
