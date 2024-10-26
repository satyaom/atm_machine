package com.atm.controller;

import com.atm.auth.CurrentUserSession;
import com.atm.enums.TransactionType;
import com.atm.model.Transaction;
import com.atm.service.TransactionService;
import com.atm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {
    @Autowired
    TransactionService transactionService;

    @Autowired
    CurrentUserSession currentUserSession;

    @GetMapping("/count")
    public ResponseEntity<Map<String, Object>> getCustomerCount() {
        Map<String, Object> res = new HashMap<>();
        res.put("customerCount", transactionService.getCustomerInLas24Hours());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> createTransactions(
            @RequestBody Transaction transaction
            ) {
        transaction.setTimestamp(OffsetDateTime.now());
        Map<String, Object> res = new HashMap<>();
        res.put("transaction", transactionService.saveTransaction(transaction));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> listTransaction(
            @RequestParam(value = "transaction_type") TransactionType transactionType
    ) {
        Map<String, Object> res = new HashMap<>();
        res.put("transactions", transactionService.listTransactionsByType(transactionType));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
