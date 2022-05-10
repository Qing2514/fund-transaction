package com.fundtrans.userManage.pojo;

import java.io.Serializable;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;


/**
 * <p>
 *
 * </p>
 *
 * @author dyoung
 * @since 2022-05-07
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 证件类型+证件号码
     */
    private String id;

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

    private String security;

    private Integer state;


}
