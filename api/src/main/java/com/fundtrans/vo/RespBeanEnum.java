package com.fundtrans.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 公共返回对象枚举
 */
@Getter
@ToString
@AllArgsConstructor
public enum RespBeanEnum {

    SUCCESS(200,"SUCCESS"),
    ERROR(500,"服务端异常"),

    USER_FINDALL_ERROR(500100,"查询失败"),
    USER_FINDBYID_ERROR(500101,"用户查询失败"),
    USER_NOT_EXIST(500102,"用户不存在"),
    USER_LIST_NULL(500103,"用户列表为空"),
    USER_INSERT_ERROR(500104,"用户插入失败"),
    USER_UPDATE_ERROR(500105,"用户更新失败"),
    USER_DELETE_ERROR(500106,"用户删除失败"),
    USER_ALREADY_EXIST(500107,"用户已注册"),

    EMPTY_ERROR(500209,"用户名或密码不能为空"),
    LOGIN_ERROR(500210,"用户名或密码不正确"),
    MOBILE_ERROR(500211,"手机号码格式不正确"),
    BIND_ERROR(500212,"参数校验异常"),
    MOBILE_NOT_EXIST(500213,"手机号码不存在"),
    PASSWORD_UPDATE_FAIL(500214,"密码更新失败"),
    SESSION_ERROR(500215,"用户不存在"),
    ADMIN_SESSION_ERROR(500216,"管理员不存在");


    private final Integer code;
    private final String message;
}
