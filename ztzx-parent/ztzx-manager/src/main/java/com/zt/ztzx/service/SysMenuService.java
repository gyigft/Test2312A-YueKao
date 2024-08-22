package com.zt.ztzx.service;

import com.zt.ztzx.model.entity.system.SysMenu;

import java.util.List;

public interface SysMenuService {


    List<SysMenu> findAll();
    List<SysMenu> findMenus(Long parentId);

    void save(SysMenu sysMenu);

    void updateById(SysMenu sysMenu);

    void removeById(Long id);
}
