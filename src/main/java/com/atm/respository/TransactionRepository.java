package com.atm.respository;

import com.atm.enums.TransactionType;
import com.atm.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(nativeQuery = true, value="SELECT COUNT(DISTINCT user_id) AS unique_user_count from transactions")
    public Integer getCustomersInLas24Hours();

    @Query(nativeQuery = true, value = "select * from transactions where transaction_type = ?1")
    public List<Transaction> getTransactionsByType(short transactionType);
}
