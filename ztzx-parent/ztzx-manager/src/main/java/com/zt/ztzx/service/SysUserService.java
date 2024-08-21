package com.zt.ztzx.service;

import com.github.pagehelper.PageInfo;
import com.zt.ztzx.exception.ZhentaoException;
import com.zt.ztzx.model.dto.system.AssginRoleDto;
import com.zt.ztzx.model.dto.system.LoginDto;
import com.zt.ztzx.model.dto.system.SysUserDto;
import com.zt.ztzx.model.entity.system.SysUser;
import com.zt.ztzx.model.vo.common.Result;
import com.zt.ztzx.model.vo.system.LoginVo;

public interface SysUserService {

    Result<LoginVo> login(LoginDto loginDto) ;

    Result<SysUser> getUserinfo(String token);

    void logout(String token);
    PageInfo<SysUser> findByPage(SysUserDto sysUserDto, Integer pageNum, Integer pageSize);

    void saveSysUser(SysUser sysUser);

    void updateSysUser(SysUser sysUser);

    void deleteById(Long userId);
    //用户分配角色
    void doAssign(AssginRoleDto assginRoleDto);
}
