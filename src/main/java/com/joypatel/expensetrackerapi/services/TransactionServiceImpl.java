package com.joypatel.expensetrackerapi.services;

import com.joypatel.expensetrackerapi.domain.Category;
import com.joypatel.expensetrackerapi.domain.Transaction;
import com.joypatel.expensetrackerapi.domain.User;
import com.joypatel.expensetrackerapi.exceptions.EtBadRequestException;
import com.joypatel.expensetrackerapi.exceptions.EtResourceNotFoundException;
import com.joypatel.expensetrackerapi.repositories.CategoryRepository;
import com.joypatel.expensetrackerapi.repositories.TransactionRepository;
import com.joypatel.expensetrackerapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryService categoryService;

    @Override
    public List<Transaction> fetchAllTransactions(Integer userId, Integer categoryId) {
        try {
            User user = userRepository.findById(userId).orElseThrow();
            Category category = categoryRepository.findById(categoryId).orElseThrow();
            return transactionRepository.findByCategoryAndCategory_User(category, user);
        } catch (Exception e) {
            throw new EtResourceNotFoundException("Resource not found");
        }
    }

    @Override
    public Transaction fetchTransactionById(Integer userId, Integer categoryId, Integer transactionId) {
        try {
            User user = userRepository.findById(userId).orElseThrow();
            Category category = categoryRepository.findById(categoryId).orElseThrow();
            return transactionRepository.findByTransactionIdAndCategoryAndCategory_User(transactionId, category, user);
        } catch (Exception e) {
            throw new EtResourceNotFoundException("Resource not found");
        }
    }

    @Override
    public Transaction addTransaction(Integer userId, Integer categoryId, Double amount, String note, Long transactionDate) {
        try {
            Category category = categoryService.fetchCategoryById(userId, categoryId);

            Transaction t = new Transaction();
            t.setCategory(category);
            t.setAmount(amount);
            t.setNote(note);
            t.setTransactionDate(transactionDate);

            // adding transaction to totalExpense
            category.addTransaction(t);
            categoryRepository.save(category);
            return transactionRepository.save(t);

        } catch (Exception e) {
            throw new EtBadRequestException("Bad request");
        }

    }

    @Override
    public void updateTransaction(Integer userId, Integer categoryId, Integer transactionId, Transaction updatedTransaction) {
        try {
            User user = userRepository.findById(userId).orElseThrow();
            Category category = categoryRepository.findById(categoryId).orElseThrow();
            Transaction t = transactionRepository.findByTransactionIdAndCategoryAndCategory_User(transactionId, category, user);
            category.increaseTotalExpense(updatedTransaction.getAmount() - t.getAmount());
            t.setAmount(updatedTransaction.getAmount());
            t.setNote(updatedTransaction.getNote());
            t.setTransactionDate(updatedTransaction.getTransactionDate());
            transactionRepository.save(t);
            categoryRepository.save(category);
        } catch (Exception e) {
            throw new EtBadRequestException("Bad request");
        }
    }

    @Override
    public void removeTransaction(Integer userId, Integer categoryId, Integer transactionId) {
        try {
            User user = userRepository.findById(userId).orElseThrow();
            Category category = categoryRepository.findById(categoryId).orElseThrow();
            Transaction t = transactionRepository.findByTransactionIdAndCategoryAndCategory_User(transactionId, category, user);
            category.removeTransaction(t);
            transactionRepository.delete(t);
            categoryRepository.save(category);

        } catch (Exception e) {
            throw new EtResourceNotFoundException("Resource not found");
        }
    }
}
