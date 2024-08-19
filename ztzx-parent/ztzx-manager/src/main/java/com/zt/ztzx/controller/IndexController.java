package com.zt.ztzx.controller;

import com.zt.ztzx.model.dto.system.LoginDto;
import com.zt.ztzx.model.vo.common.Result;
import com.zt.ztzx.model.vo.system.LoginVo;
import com.zt.ztzx.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 天山一剑卓不凡
 */
@RestController
@RequestMapping("admin/system/index")
@Tag(name = "首页统一接口", description = "首页接口")
public class IndexController {
    @GetMapping("index")
    //对具体接口的声明描述
    @Operation(summary = "首页接口方法", description = "首页接口11", method = "index方法")
    //参数声明注解
    @Parameters(value = {@Parameter(name = "id", description = "携带id参数", required = true)})
    public String index(@RequestParam("id") int id) {
        return "admin index";
    }

    @Autowired
    private SysUserService sysUserService;

    //登录接口
    @PostMapping("login")
    @Operation(summary = "登录接口方法", description = "登录接口", method = "login方法")
    @Parameters(value = {@Parameter(name = "loginDto", description = "登录接口请求参数", required = true)})
    public Result<LoginVo> login(@RequestBody LoginDto loginDto) {
        System.out.println(loginDto);
        return sysUserService.login(loginDto);
    }


}
