package com.zt.ztzx.service;

import com.github.pagehelper.PageInfo;
import com.zt.ztzx.model.dto.system.SysRoleDto;
import com.zt.ztzx.model.entity.system.SysRole;

public interface SysRoleService {
    public abstract PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer pageNum, Integer pageSize);

    void saveSysRole(SysRole sysRole);

    void updateSysRole(SysRole sysRole);

    void deleteById(Long roleId);
}
