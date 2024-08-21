package com.zt.ztzx.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @Author: 天山一剑卓不凡
 */
//自动配置加载配置文件属性
@ConfigurationProperties(prefix = "ztzx.manager")
@Data
public class AuthProperties {
    private List<String> noAuthUrls;
}
