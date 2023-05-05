package com.fund.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(value = "新增产品校验参数")
@Data
public class ProductVo {

    @NotBlank(message = "产品代码不能为空")
    private String id;

    @NotBlank(message = "产品名称不能为空")
    private String name;

    @NotBlank(message = "产品类型不能为空")
    private String type;

    @NotNull(message = "风险等级不能为空")
    private Integer security;

    @NotBlank(message = "产品经理不能为空")
    private String manager;

}
