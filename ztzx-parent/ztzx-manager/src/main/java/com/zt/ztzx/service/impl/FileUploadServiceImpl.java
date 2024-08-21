package com.zt.ztzx.service.impl;

import cn.hutool.core.util.IdUtil;
import com.zt.ztzx.properties.MinioProperties;
import com.zt.ztzx.service.FileUploadService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: 天山一剑卓不凡
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private MinioProperties minioProperties;
    //上传逻辑
    @Override
    public String uplaod(MultipartFile file) {
        try {
            //minio开发流程
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(minioProperties.getEndpointUrl())
                    .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                    .build();
            //创建bucket
//        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("ztzx-bucket").build());

//        FileInputStream fileInputStream = new FileInputStream("D:\\apple.png");
            //获取输入流
//        InputStream inputStream = file.getInputStream();
            //文件名称
            String originalFilename = file.getOriginalFilename();
            //文件保存在minio中防止文件名重复，日期+uuid拼接文件名
            String s = IdUtil.simpleUUID();

            String imgUrl = s+originalFilename;
            minioClient.putObject(PutObjectArgs.builder().bucket(minioProperties.getBucketName())
                    .stream(file.getInputStream(), file.getInputStream().available(), -1)
                    .object(imgUrl).build());
            //返回完整的图片路径
            return minioProperties.getEndpointUrl()+"/"+minioProperties.getBucketName()+"/"+imgUrl;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
