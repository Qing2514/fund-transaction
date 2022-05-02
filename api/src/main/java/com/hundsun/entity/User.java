package com.hundsun.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Qing2514
 * @date 2022-05-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 证件类型+证件号码
     */
    private String id;

    private String name;

    private String type;

    /**
     * 证件类型
     */
    private String ctype;

    /**
     * 证件号
     */
    private String cid;

    private String password;

    private String phone;

    private Integer age;

    private String sex;

    private String address;

    private String job;

    private String security;


}
