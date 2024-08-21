package com.zt.ztzx.mapper;

import com.zt.ztzx.model.dto.system.SysRoleDto;
import com.zt.ztzx.model.entity.system.SysRole;

import java.util.List;

public interface SysRoleMapper {
    public abstract List<SysRole> findByPage(SysRoleDto sysRoleDto);

    void saveSysRole(SysRole sysRole);

    void updateSysRole(SysRole sysRole);

    void deleteById(Long roleId);
    void delete(Long roleId);

    List<SysRole> findAll();
}
