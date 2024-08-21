package com.zt.ztzx.utils;

import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;

import java.io.FileInputStream;

/**
 * @Author: 天山一剑卓不凡
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        //minio开发流程
        MinioClient minioClient = MinioClient.builder()
                .endpoint("http://localhost:9000")
                .credentials("minioadmin", "minioadmin")
                .build();
        //创建bucket
//        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("ztzx-bucket").build());

        FileInputStream fileInputStream = new FileInputStream("D:\\apple.png");
        minioClient.putObject(PutObjectArgs.builder().bucket("yjs2312")
                        .stream(fileInputStream, fileInputStream.available(), -1)
                .object("01.png").build());
        System.out.println("上传成功");
        //上传成功要得到的是文件url地址保存到数据库中
    }
}
