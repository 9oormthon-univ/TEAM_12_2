package com.letsRoll.letsRoll.global.enums;

import lombok.Getter;

@Getter
public enum Common {

    USER_ID("userId"),
    USER_NAME("userName"),
    SPACE(" "),
    COMMA(",");

    private String value;
    Common(String value) {
        this.value = value;
    }
}
