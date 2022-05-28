package com.fundtrans.pojo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


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

    private Integer state;


}
