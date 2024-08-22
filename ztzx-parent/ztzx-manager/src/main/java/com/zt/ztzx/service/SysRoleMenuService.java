package com.zt.ztzx.service;

import com.zt.ztzx.model.vo.system.SysMenuVo;

import java.util.List;
import java.util.Map;

public interface SysRoleMenuService {
    Map<String, Object> findSysRoleMenuByRoleId(Long roleId);

    List<SysMenuVo> findSysMenus();
}
