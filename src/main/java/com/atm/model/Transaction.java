package com.atm.model;

import com.atm.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "atm_id")
    private Long atmId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "timestamp", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime timestamp;

    @Column(name = "amount")
    private Double amount;
}
