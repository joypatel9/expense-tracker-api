package com.joypatel.expensetrackerapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "et_category")
@Getter
@Setter
@ToString(exclude = "user")
public class Category {

    @Id
    @GeneratedValue
    private Integer categoryId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = ALL)
    private Set<Transaction> transactions;

    private String title;
    private String description;
    private Double totalExpense = 0.0;

    public void addTransaction(Transaction transaction) {
        totalExpense += transaction.getAmount();
    }

    public void removeTransaction(Transaction transaction) {
        totalExpense -= transaction.getAmount();
    }

    public void increaseTotalExpense(double amount) {
        totalExpense += amount;
    }
}
