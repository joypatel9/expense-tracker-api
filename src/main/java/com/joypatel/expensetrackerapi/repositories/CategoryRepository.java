package com.joypatel.expensetrackerapi.repositories;

import com.joypatel.expensetrackerapi.domain.Category;
import com.joypatel.expensetrackerapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findByUser(User user);

    Optional<Category> findByCategoryIdAndUser(Integer categoryId, User user);

}
