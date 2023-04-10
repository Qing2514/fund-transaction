package com.fund.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum ResultEnum {

    SUCCESS(200,"SUCCESS"),
    ERROR(500,"服务端异常"),
    BIND_ERROR(500009,"参数校验异常"),

    USER_NOT_EXIST(500101,"用户不存在"),
    USER_ALREADY_EXIST(500102,"用户已开户"),

    CARD_ALREADY_BIND(500300,"该银行卡已绑定"),
    CARD_INSERT_FAIL(500301,"银行卡绑定失败"),
    CARD_NOT_BIND(500302,"银行卡未绑定"),
    CARD_UNBIND_FAIL(500303,"银行卡解绑失败"),
    CARD_NOT_EXIST(500304,"银行卡不存在"),
    CARD_CANT_TOPUP(500305,"该用户与该银行卡不存在绑定关系，无法进行充值"),
    CARD_TOPUP_FAIL(500306,"银行卡充值失败"),
    CARD_FIND_FAIL(500307,"查询客户银行卡失败"),
    CARD_NUM_EMPTY(500308,"客户绑定银行卡数量为0，请进行绑定"),
    CARD_UPDATE_FAIL(500309,"银行卡余额扣减失败"),
    CARD_ACCOUNT_UPDATE_ERROR(500310,"银行卡余额更新失败"),

    PURCHASE_UPDATECOUNT_ERROR(500200,"更新申购表份额失败"),
    PURCHASE_ADD_ERROR(500201,"申购记录添加失败"),
    PURCHASE_RELOAD_ERROR(500202,"重新生成当天申购订单失败"),

    BALANCE_NOT_AVAILABLE(500400,"该卡余额不足"),
    PTRANS_INSERT_FAIL(500401,"申购交易记录添加失败"),
    PTRANS_FINDBYID_FAIL(500402,"根据用户查询申购交易记录失败"),
    PTRASN_FINDBYPID_FAIL(500403,"根据申购记录ID查询失败"),
    PTRANS_NOT_EXIST(500404,"申购交易记录不存在"),
    PTRANS_STATE_UPDATE_FAIL(500405,"申购记录状态字段更新失败"),
    PTRANS_ALREADY_PROCESS(500406,"申购记录已处理或者已撤回，无法进行撤回"),
    PTRANS_WITHDRAW_ERROR(500407,"超过规定撤回时间，无法对交易进行撤回"),

    PRODUCT_ALREADY_EXIST(500501,"产品代码已存在"),
    PRODUCT_INSERT_ERROR(500502,"产品添加失败"),
    PRODUCT_NOT_EXIST(500503,"产品不存在"),
    PRODUCT_UPDATE_ERROR(500504,"产品更新失败"),
    PRODUCT_FINDALL_ERROR(500505,"产品信息错误"),
    PRODUCT_LIST_NULL(500506,"产品列表为空"),
    PRODUCT_NOTFIND_ERROR(500507,"无指定产品"),
    PRODUCT_DELETE_ERROR(500508,"产品删除失败"),
    PRICE_INSERT_ERROR(500509,"净值增加失败"),
    PRODUCT_FIND_FAIL(500510,"基金查询失败"),

    RTRANS_ADD_ERROR(500600,"添加赎回交易记录失败"),
    RTRANS_COUNT_BEYOND(500601,"赎回份额不能大于该卡已购份额"),
    RTRANS_FIND_ERROR(500602,"用户赎回交易记录查询失败"),
    RTRANS_NOT_EXIST(500603,"赎回交易记录不存在"),
    RTRANS_ALREADY_PROCEED(500604,"赎回交易记录已处理或者已撤回，无法进行撤回"),
    RTRANS_WITHDRAW_ERROR(500605,"超过规定撤回时间，无法对交易进行撤回"),
    RTRANS_STATE_UPDATE_FAIL(500606,"赎回记录状态字段更新失败"),

    SHARE_FIND_ERROR(500700,"份额查询失败"),
    SHARE_NO_PURCHASE(500701,"该用户未购买该基金产品，无法进行赎回"),
    SHARE_CARD_NO_PURCHASE(500702,"该用户未用该卡进行申购，无法赎回到该卡中"),
    SHARE_FROZEN_UPDATE_ERROR(500703,"冻结份额更新失败"),
    SHARE_DELETE_ERROR(500704,"份额记录删除失败"),
    SHARE_UPDATE_ERROR(500705,"份额表更新失败"),

    REDEMPTION_ADD_ERROR(500800,"赎回记录添加失败"),
    REDEMPTION_UPDATEAMOUNT_ERROR(500801,"更新赎回表份额失败"),
    REDEMPTION_RELOAD_ERROR(500802,"重新生成当天赎回订单失败"),

    RECORD_ADD_ERROR(500900,"份额流水记录添加失败"),
    RECORD_FIND_ERROR(500901,"份额流水记录查询失败"),
    RECORD_NOT_EXIST(500902,"该份额流水记录不存在"),

    DATE_PARSE_ERROR(501000,"日初始化失败"),
    TREND_UPDATE_ERROR(501001,"更新行情失败"),
    TREND_FIND_ERROR(501002,"净值查询失败"),
    TREND_NOT_EXIST(501003,"指定查询条件下无净值");

    private final Integer code;
    private final String message;
}
