package com.atm.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN(0), CUSTOMER(1);
    private final int value;

    UserRole(int value) {this.value = value;}
}
