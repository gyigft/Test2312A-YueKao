package com.zt.ztzx.exception;

import com.zt.ztzx.model.vo.common.ResultCodeEnum;
import lombok.Data;

/**
 * @Author: 天山一剑卓不凡
 */
@Data
//全局异常
public class ZhentaoException  extends RuntimeException{

    private Integer code;
    private String message;
    private ResultCodeEnum resultCodeEnum;
    public ZhentaoException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public ZhentaoException(Integer code,ResultCodeEnum resultCodeEnum, String message) {
        this.code = code;
        this.message = message;
        this.resultCodeEnum=resultCodeEnum;

    }
    public ZhentaoException(ResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum ;
        this.code = resultCodeEnum.getCode() ;
        this.message = resultCodeEnum.getMessage();
    }



}
