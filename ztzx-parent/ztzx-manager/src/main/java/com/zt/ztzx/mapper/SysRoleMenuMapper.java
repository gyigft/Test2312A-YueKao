package com.zt.ztzx.mapper;

import com.zt.ztzx.model.entity.system.SysMenu;

import java.util.List;

public interface SysRoleMenuMapper {
    public abstract List<Long> findSysRoleMenuByRoleId(Long roleId);


    List<SysMenu> findSysMenus(Long userId);


}
