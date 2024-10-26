package com.atm.model;

import com.atm.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "verified")
    private Boolean verified;

    @Column(name = "role", columnDefinition = "SMALLINT")
    private UserRole role;
}
