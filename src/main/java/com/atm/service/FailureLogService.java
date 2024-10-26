package com.atm.service;

import com.atm.enums.FailureType;
import com.atm.model.FailureLog;
import com.atm.respository.FailureLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FailureLogService {

    @Autowired
    FailureLogRepository failureLogRepository;

    public FailureLog saveFailureLog(FailureLog failureLog) {
        return failureLogRepository.save(failureLog);
    }

    public List<FailureLog> listFailureLongsByType(FailureType failureType) {
        return failureLogRepository.getFailureLongsByType((short) failureType.getValue());
    }
}
