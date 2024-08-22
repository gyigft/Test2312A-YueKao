package com.zt.ztzx.mapper;

import java.util.List;

public interface SysRoleMenuMapper {
    public abstract List<Long> findSysRoleMenuByRoleId(Long roleId);
}
