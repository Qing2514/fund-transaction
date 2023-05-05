package com.fund.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {

    /**
     * 返回结果枚举
     */
    SUCCESS(0,"SUCCESS"),
    ERROR(500,"ERROR"),

    USER_NOT_EXIST(500101,"客户不存在"),
    CID_OR_PHONE_ALREADY_USE(500102,"证件号或手机号已使用"),

    PRODUCT_ALREADY_EXIST(500201,"产品已存在"),
    PRODUCT_NOT_EXIST(500202,"产品不存在"),

    CARD_NOT_EXIST(500301,"银行卡不存在"),
    CARD_EXIST_OR_USER_NOT_EXIST(500302,"银行卡已绑定或客户不存在"),
    CARD_NOT_EXIST_OR_HAVING_ORDERS(500303,"银行卡不存在或有进行中订单"),

    PURCHASE_NOT_EXIST(500401,"申购订单不存在"),
    CARD_NOT_EXIST_OR_NOT_BELONG_OR_LACK_AMOUNT(500402,"银行卡不存在，或银行卡不属于该客户，或银行卡余额不足"),
    NET_WORTH_NOT_EXIST(500403,"该天净值不存在"),

    REDEMPTION_NOT_EXIST(500501,"赎回订单不存在"),
    CARD_OR_USER_OR_PRODUCT_NOT_EXIST_OR_LACK_SHARE(500502,"银行卡或客户或产品不存在，或拥有份额不足"),

    DATE_ERROR(500601,"该天为周末或该天净值已更新"),

    ;

    private final Integer code;
    private final String message;

}
