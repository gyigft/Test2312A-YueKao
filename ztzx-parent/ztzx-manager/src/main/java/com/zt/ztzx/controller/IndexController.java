package com.zt.ztzx.controller;

import com.zt.ztzx.model.dto.system.LoginDto;
import com.zt.ztzx.model.entity.system.SysUser;
import com.zt.ztzx.model.vo.common.Result;
import com.zt.ztzx.model.vo.system.LoginVo;
import com.zt.ztzx.model.vo.system.SysMenuVo;
import com.zt.ztzx.service.SysRoleMenuService;
import com.zt.ztzx.service.SysUserService;
import com.zt.ztzx.utils.AuthContextUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 天山一剑卓不凡
 */
@RestController
@RequestMapping("admin/system/index")
@Tag(name = "首页统一接口", description = "首页接口")
//@CrossOrigin(origins = "*",originPatterns = "*", allowedHeaders = "*",allowCredentials = "true",)  //解决跨域的注解
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
    //获取用户信息,
    @GetMapping("userinfo")
    @Operation(summary = "获取用户信息接口方法", description = "获取用户信息接口", method = "info方法")
    //spirngmvc中如何获取请求头信息
//    public Result<SysUser> info(@RequestHeader(name = "token")String token) {
    public Result<SysUser> info() {

        //从当前线程中取出用户信息
        SysUser user = AuthContextUtil.getUser();
//        return sysUserService.getUserinfo(token);
        return Result.build(user, 200, "获取用户信息成功");
    }
    //用户退出
    @GetMapping("logout")
    @Operation(summary = "用户退出接口方法", description = "用户退出接口", method = "logout方法")
    public Result logout(@RequestHeader(name = "token")String token) {
        sysUserService.logout(token);
        return Result.build(null, 200, "退出成功");
    }

    //查询当前用户的菜单

    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    @GetMapping("menus")
    @Operation(summary = "查询当前用户的菜单接口方法", description = "查询当前用户的菜单接口", method = "menus方法")
    public Result<List<SysMenuVo>> menus() {
        List<SysMenuVo> list =sysRoleMenuService.findSysMenus();
        return Result.build(list, 200, "查询当前用户的菜单成功");
    }


}
