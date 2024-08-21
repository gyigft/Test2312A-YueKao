package com.zt.ztzx.config;

import com.zt.ztzx.interceptor.AuthLoginInterceptor;
import com.zt.ztzx.properties.AuthProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: 天山一剑卓不凡
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Autowired
    private AuthLoginInterceptor authLoginInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*") // 或者指定的模式列表
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .maxAge(3600);
    }

    @Autowired
    private AuthProperties authProperties;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authLoginInterceptor)
                //放行路径
                .excludePathPatterns(authProperties.getNoAuthUrls()).addPathPatterns("/**");
    }
}