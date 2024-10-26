package com.atm.enums;

import lombok.Getter;

@Getter
public enum MediaType {
    IMAGE(0), VIDEO(1);
    private final int val;

    MediaType(int val) {
        this.val = val;
    }
}
