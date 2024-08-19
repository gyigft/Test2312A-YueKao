package com.zt.ztzx.controller;

import com.zt.ztzx.model.entity.product.Product;
import com.zt.ztzx.model.vo.common.Result;
import com.zt.ztzx.model.vo.common.ResultCodeEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 天山一剑卓不凡
 */
//业务接口，必须登录才可以访问
@RestController
@RequestMapping("admin/system/product")
public class ProductController {

    @GetMapping("findAll")
    public Result<List<Product>> findAll(){
        List<Product> list = new ArrayList<>();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

}
