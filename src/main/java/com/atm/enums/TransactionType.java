package com.atm.enums;

import lombok.Getter;

@Getter
public enum TransactionType {
    DEPOSIT(0), WITHDRAWAL(1), BALANCE_INQUIRY(2);
    private final int value;

    TransactionType(int value) {
        this.value = value;
    }
}
