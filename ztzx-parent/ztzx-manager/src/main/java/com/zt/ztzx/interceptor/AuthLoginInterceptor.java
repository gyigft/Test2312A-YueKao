package com.zt.ztzx.interceptor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.zt.ztzx.model.entity.system.SysUser;
import com.zt.ztzx.model.vo.common.Result;
import com.zt.ztzx.model.vo.common.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 天山一剑卓不凡
 */
//拦截器实现原理：AOP思想
@Component
public class AuthLoginInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //目录资源访问之前执行  true 放行 false 拦截
    //前置通知
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求方式
        String method = request.getMethod();
        if("OPTIONS".equals(method)) {      // 如果是跨域预检请求，直接放行
            return true ;
        }

        // 获取token
        String token = request.getHeader("token");
        if(StrUtil.isEmpty(token)) {
            responseNoLoginInfo(response) ;
            return false ;
        }

        // 如果token不为空，那么此时验证token的合法性
        String sysUserInfoJson = redisTemplate.opsForValue().get("user:login:" + token);
        if(StrUtil.isEmpty(sysUserInfoJson)) {
            responseNoLoginInfo(response) ;
            return false ;
        }

        // 将用户数据存储到ThreadLocal中
        SysUser sysUser = JSON.parseObject(sysUserInfoJson, SysUser.class);
//        AuthContextUtil.set(sysUser);

        // 重置Redis中的用户数据的有效时间
        redisTemplate.expire("user:login:" + token , 30 , TimeUnit.MINUTES) ;

        // 放行
        return true ;

    }
    //响应208状态码给前端
    private void responseNoLoginInfo(HttpServletResponse response) {
        Result<Object> result = Result.build(null, ResultCodeEnum.LOGIN_AUTH);
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JSON.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
        }
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
