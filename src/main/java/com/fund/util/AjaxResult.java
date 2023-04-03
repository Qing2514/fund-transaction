package com.fund.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公共返回对象
 */
@NoArgsConstructor
@AllArgsConstructor
public class AjaxResult<T> {

    private Integer code;
    private String msg;
    private T data;

    public AjaxResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public AjaxResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public static AjaxResult success(){
        return new AjaxResult(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), null);
    }

    public static AjaxResult success(Object obj){
        return new AjaxResult(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), obj);
    }

    public static AjaxResult error(ResultEnum resultEnum){
        return new AjaxResult(resultEnum.getCode(), resultEnum.getMessage(), null);
    }

    public static AjaxResult error(ResultEnum resultEnum, Object obj){
        return new AjaxResult(resultEnum.getCode(), resultEnum.getMessage(), obj);
    }
}
