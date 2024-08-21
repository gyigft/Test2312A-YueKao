package com.zt.ztzx.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: 天山一剑卓不凡
 */
@Data
@ConfigurationProperties(prefix = "ztzx.minio")
public class MinioProperties {
    private String endpointUrl;
    private String accessKey;
    private String secretKey;
    private String bucketName;
}
