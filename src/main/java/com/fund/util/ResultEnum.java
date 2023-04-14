package com.fund.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {

    /**
     * 返回结果枚举
     */
    SUCCESS(200,"SUCCESS"),
    ERROR(500,"ERROR"),

    USER_NOT_EXIST(500101,"用户不存在"),
    USER_ALREADY_EXIST(500102,"用户已开户"),

    PRODUCT_ALREADY_EXIST(500201,"产品代码已存在"),
    PRODUCT_NOT_EXIST(500202,"产品不存在"),

    CARD_NOT_EXIST(500301,"银行卡不存在"),
    CARD_EXIST_OR_USER_NOT_EXIST(500302,"银行卡已绑定或用户不存在"),

    PURCHASE_NOT_EXIST(500401,"申购订单不存在"),
    CARD_OR_USER_OR_PRODUCT_NOT_EXIST(500402,"银行卡或用户或产品不存在，或银行卡余额不足"),
    NET_WORTH_NOT_EXIST(500403,"该天净值不存在"),

    DATE_ERROR(500601,"该天为周末或该天净值已更新"),

    ;

    private final Integer code;
    private final String message;

}
