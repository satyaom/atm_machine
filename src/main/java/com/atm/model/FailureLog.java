package com.atm.model;

import com.atm.enums.FailureType;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "failure_logs")
public class FailureLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "atm_id")
    private Long atmId;

    @Column(name = "failure_type", columnDefinition = "SMALLINT")
    private FailureType failureType;

    @Column(name = "timestamp", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime timestamp;

    @Column(name = "detail")
    private String detail;
}
