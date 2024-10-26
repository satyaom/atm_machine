package com.atm.respository;

import com.atm.model.FailureLog;
import com.atm.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FailureLogRepository extends JpaRepository<FailureLog, Long> {
    @Query(nativeQuery = true, value = "select * from failure_logs where failure_type = ?1")
    public List<FailureLog> getFailureLongsByType(short failureType);
}
