package com.fundtrans.userManage.vo;

import lombok.Data;

@Data
public class UserSearch {
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

    private int symbol = 0;
}
