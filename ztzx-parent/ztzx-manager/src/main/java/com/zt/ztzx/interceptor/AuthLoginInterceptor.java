package com.zt.ztzx.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: 天山一剑卓不凡
 */
//拦截器实现原理：AOP思想
@Component
public class AuthLoginInterceptor implements HandlerInterceptor {

    //目录资源访问之前执行  true 放行 false 拦截
    //前置通知
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器执行了");
        //OPTIONS请求，直接放行,跨域预检请求
        String method = request.getMethod();
        if ("OPTIONS".equals(method)){
            return true;
        }
        //有token信息就放行
        String token = request.getHeader("token");
        //对token进行判断

        //没有token拦截，给定指定结果
        return false;

    }

    //目录资源访问之后执行
    //后置通知
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
    //资源访问完成之后执行
    //
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
