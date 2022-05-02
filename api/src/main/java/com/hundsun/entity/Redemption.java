package com.hundsun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Qing2514
 * @date 2022-05-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Redemption implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userId;

    private String productId;

    private String cardId;

    private Date time;

    private BigDecimal count;

    /**
     * 记录状态：0表示已撤回 1表示未撤回
     */
    private Integer state;


}
