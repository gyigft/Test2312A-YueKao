package com.zt.ztzx.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 天山一剑卓不凡
 */
@RestController
@RequestMapping("/admin/system")
public class FileUploadController {

    @PostMapping("fileUpload")
    public String fileUpload(MultipartFile file) {

        return "";
    }
}
