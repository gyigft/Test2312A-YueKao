package com.zt.ztzx.controller;

import com.zt.ztzx.model.entity.system.SysMenu;
import com.zt.ztzx.model.vo.common.Result;
import com.zt.ztzx.model.vo.common.ResultCodeEnum;
import com.zt.ztzx.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 天山一剑卓不凡
 */

@RestController
@RequestMapping(value="/admin/system/sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping("/findNodes")
    public Result<List<SysMenu>> findNodes() {
        List<SysMenu> list = sysMenuService.findAll();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }
    @PostMapping("/save")
    public Result save(@RequestBody SysMenu sysMenu) {
        sysMenuService.save(sysMenu);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    @PutMapping("/updateById")
    public Result updateById(@RequestBody SysMenu sysMenu) {
        sysMenuService.updateById(sysMenu);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    @DeleteMapping("/removeById/{id}")
    public Result removeById(@PathVariable Long id) {
        sysMenuService.removeById(id);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
}