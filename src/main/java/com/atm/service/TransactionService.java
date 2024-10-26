package com.atm.service;

import com.atm.enums.TransactionType;
import com.atm.model.Transaction;
import com.atm.respository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> listTransactionsByType(TransactionType transactionType) {
        return transactionRepository.getTransactionsByType((short) transactionType.getValue());
    }

    public Integer getCustomerInLas24Hours() {
        OffsetDateTime now = OffsetDateTime.now();
        OffsetDateTime last24Hours = now.minusHours(24);
        return transactionRepository.getCustomersInLas24Hours(now, last24Hours);
    }
}
