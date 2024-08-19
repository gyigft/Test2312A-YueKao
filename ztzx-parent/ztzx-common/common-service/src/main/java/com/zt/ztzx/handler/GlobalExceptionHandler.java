package com.zt.ztzx.handler;

import com.zt.ztzx.exception.ZhentaoException;
import com.zt.ztzx.model.vo.common.Result;
import com.zt.ztzx.model.vo.common.ResultCodeEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: 天山一剑卓不凡
 */
@ControllerAdvice //对所有的@Controller注解修饰的类做增强
//@RestControllerAdvice
//AOP面向切面  切面=切点+通知    切点：目标需要继续增强的方法，逻辑    通知：增强的逻辑
public class GlobalExceptionHandler {

    @ExceptionHandler(ZhentaoException.class)
    @ResponseBody
    public Result handleException(Exception e) {
        e.printStackTrace();
        return Result.build(null, ResultCodeEnum.DATA_ERROR.getCode(), ResultCodeEnum.DATA_ERROR.getMessage());
    }
}
