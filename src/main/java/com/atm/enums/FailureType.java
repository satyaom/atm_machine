package com.atm.enums;

import lombok.Getter;

@Getter
public enum FailureType {
    DEVICE_FAILURE(0), SYSTEM_FAILURE(1);
    private final int value;

    FailureType(int value) {this.value = value;}
}
