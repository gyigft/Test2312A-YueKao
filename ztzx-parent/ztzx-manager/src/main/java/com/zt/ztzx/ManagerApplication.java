package com.zt.ztzx;

import com.zt.ztzx.properties.AuthProperties;
import com.zt.ztzx.properties.MinioProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @Author: 天山一剑卓不凡
 */
@SpringBootApplication
@MapperScan("com.zt.ztzx.mapper")
@EnableConfigurationProperties(value = {AuthProperties.class, MinioProperties.class})
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }
}
