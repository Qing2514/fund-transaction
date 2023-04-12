package com.fund.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum ResultEnum {

    SUCCESS(200,"SUCCESS"),
    ERROR(500,"ERROR"),

    USER_NOT_EXIST(500101,"用户不存在"),
    USER_ALREADY_EXIST(500102,"用户已开户"),

    PRODUCT_ALREADY_EXIST(500201,"产品代码已存在"),
    PRODUCT_NOT_EXIST(500202,"产品不存在"),

    CARD_NOT_EXIST(500301,"银行卡不存在"),
    CARD_EXIST_OR_USER_NOT_EXIST(500302,"银行卡已绑定或用户不存在");

    private final Integer code;
    private final String message;
}
