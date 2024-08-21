package com.zt.ztzx.controller;

import com.zt.ztzx.model.vo.common.Result;
import com.zt.ztzx.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 天山一剑卓不凡
 */
@RestController
@RequestMapping("/admin/system")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;
    //文件上传三要素  请求方法post ,请求参数MultipartFile file要和前端form表单标签元素的name属性一致  form的enctype属性为multipart/form-data
    @PostMapping("fileUpload")
    public Result<String> fileUpload(@RequestParam("file") MultipartFile file) {
        String uplaod = fileUploadService.uplaod(file);
        return Result.build(uplaod, 200,"上传成功");
    }
}
