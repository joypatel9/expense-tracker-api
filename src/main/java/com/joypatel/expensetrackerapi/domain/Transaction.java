package com.joypatel.expensetrackerapi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "et_transaction")
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue
    private Integer transactionId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false)
    private Double amount = 0.0;

    private String note;
    private Long transactionDate;
}
