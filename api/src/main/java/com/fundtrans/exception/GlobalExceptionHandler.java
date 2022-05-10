package com.fundtrans.exception;

import com.fundtrans.vo.RespBean;
import com.fundtrans.vo.RespBeanEnum;
import com.hundsun.jrescloud.common.exception.BaseBizException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public RespBean ExceptionHandler(Exception e){
        if(e instanceof GlobalException){
            GlobalException ex = (GlobalException) e;
            return RespBean.error(ex.getRespBeanEnum());
        }
        if(e instanceof BindException){
//            注解@NotNull等出现的异常
            BindException ex = (BindException) e;
            RespBean respBean = RespBean.error(RespBeanEnum.BIND_ERROR);
//            将绑定异常抛出来的信息显示出来
            respBean.setMessage("参数校验异常："+ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
            return respBean;
        }
        if (e instanceof BaseBizException){
            BaseBizException be = (BaseBizException) e;
            RespBean respBean = RespBean.error(RespBeanEnum.ERROR);
            respBean.setCode(Long.parseLong(be.getErrorCode()));
            respBean.setMessage(be.getErrorMessage());
            return respBean;
        }
        return RespBean.error(RespBeanEnum.ERROR);
    }
}
