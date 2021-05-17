package com.joypatel.expensetrackerapi.services;

import com.joypatel.expensetrackerapi.domain.Category;
import com.joypatel.expensetrackerapi.domain.User;
import com.joypatel.expensetrackerapi.exceptions.EtBadRequestException;
import com.joypatel.expensetrackerapi.exceptions.EtResourceNotFoundException;
import com.joypatel.expensetrackerapi.repositories.CategoryRepository;
import com.joypatel.expensetrackerapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Category> fetchAllCategories(Integer userId) {
        User user = userRepository.findById(userId).get();
        return categoryRepository.findByUser(user);
    }

    @Override
    public Category fetchCategoryById(Integer userId, Integer categoryId) {
        try {
            User user = userRepository.findById(userId).get();
            return categoryRepository.findByCategoryIdAndUser(categoryId, user).get();
        } catch (Exception e) {
            throw new EtResourceNotFoundException("Resource not found!");
        }
    }

    @Override
    public Category addCategory(Integer userId, String title, String description) {
        try {
            User user = userRepository.findById(userId).orElseThrow();

            Category category = new Category();
            category.setUser(user);
            category.setTitle(title);
            category.setDescription(description);

            return categoryRepository.save(category);
        } catch (Exception e) {
            throw new EtBadRequestException("Bad Request!");
        }
    }

    @Override
    public void updateCategory(Integer userId, Integer categoryId, Category updatedCategory) {
        try {
            Category category = fetchCategoryById(userId, categoryId);
            category.setTitle(updatedCategory.getTitle());
            category.setDescription(updatedCategory.getDescription());

            categoryRepository.save(category);
        } catch (Exception e) {
            throw new EtBadRequestException("Bad Request");
        }
    }

    @Override
    public void removeCategoryWithAllTransactions(Integer userId, Integer categoryId) {
        try {
            Category category = fetchCategoryById(userId, categoryId);
            categoryRepository.delete(category);
        } catch (Exception e) {
            throw new EtResourceNotFoundException("Resource not found");
        }
    }
}
