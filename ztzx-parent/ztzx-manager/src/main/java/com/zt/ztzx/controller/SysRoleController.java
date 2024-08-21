package com.zt.ztzx.controller;

import com.github.pagehelper.PageInfo;
import com.zt.ztzx.model.dto.system.SysRoleDto;
import com.zt.ztzx.model.entity.system.SysRole;
import com.zt.ztzx.model.vo.common.Result;
import com.zt.ztzx.model.vo.common.ResultCodeEnum;
import com.zt.ztzx.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: 天山一剑卓不凡
 */
@RestController
@RequestMapping("admin/system/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @PostMapping("findPage/{currentPage}/{pageSize}")
    public Result<PageInfo<SysRole>> findPage(@RequestBody SysRoleDto sysRoleDto, @PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize) {
        PageInfo<SysRole> pageInfo = sysRoleService.findByPage(sysRoleDto, currentPage, pageSize);
        return Result.build(pageInfo, 200, "success");

    }

    @PostMapping(value = "/saveSysRole")
    public Result saveSysRole(@RequestBody SysRole SysRole) {
        sysRoleService.saveSysRole(SysRole);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    // com.zt.ztzx.manager.controller.SysRoleController
    @PutMapping(value = "/updateSysRole")
    public Result updateSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.updateSysRole(sysRole);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @DeleteMapping(value = "/deleteById/{roleId}")
    public Result deleteById(@PathVariable(value = "roleId") Long roleId) {
        sysRoleService.deleteById(roleId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    //查询所有角色
    @GetMapping("/findAllRoles/{userId}")
    public Result findAllRoles(@PathVariable("userId") Long userId) {
        Map<String,Object> map = sysRoleService.findAll(userId);
        return Result.build(map,ResultCodeEnum.SUCCESS);
    }

}
