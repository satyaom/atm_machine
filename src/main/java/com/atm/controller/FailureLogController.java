package com.atm.controller;

import com.atm.enums.FailureType;
import com.atm.model.FailureLog;
import com.atm.service.FailureLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("failure_logs")
public class FailureLogController {

    @Autowired
    FailureLogService failureLogService;

    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Map<String, Object>> saveFailureLog(
            @RequestBody FailureLog failureLog
    ) {
        failureLog.setTimestamp(OffsetDateTime.now());
        Map<String, Object> res = new HashMap<>();
        res.put("failureLog", failureLogService.saveFailureLog(failureLog));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Map<String, Object>> listFailureLog(
            @RequestParam(value = "failure_type") FailureType failureType
    ) {
        Map<String, Object> res = new HashMap<>();
        res.put("failureLogs", failureLogService.listFailureLongsByType(failureType));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
