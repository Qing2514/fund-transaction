package com.fund.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AjaxResult {

    private Integer code;
    private String msg;
    private Object data;

    public AjaxResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public AjaxResult(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public static AjaxResult success(){
        return new AjaxResult(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), null);
    }

    public static AjaxResult success(Object obj){
        return new AjaxResult(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), obj);
    }

    public static AjaxResult error(){
        return new AjaxResult(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMessage(), null);
    }

    public static AjaxResult error(ResultEnum resultEnum){
        return new AjaxResult(resultEnum.getCode(), resultEnum.getMessage(), null);
    }

}
