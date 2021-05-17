package com.joypatel.expensetrackerapi.repositories;

import com.joypatel.expensetrackerapi.domain.Category;
import com.joypatel.expensetrackerapi.domain.Transaction;
import com.joypatel.expensetrackerapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByCategoryAndCategory_User(Category category, User user);

    Transaction findByTransactionIdAndCategoryAndCategory_User(Integer transactionId, Category category, User user);

}
