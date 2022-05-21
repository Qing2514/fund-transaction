package com.fundtrans.userManage.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserVo {
    @NotNull
    private String name;

    @NotNull
    private String type;

    /**
     * 证件类型
     */
    @NotNull
    private String ctype;

    /**
     * 证件号
     */
    @NotNull
    private String cid;

    private String password;

    private String phone;

    private Integer age;

    private String sex;

    private String address;

    private String job;
}
