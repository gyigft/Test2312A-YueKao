package com.zt.ztzx.mapper;

import com.zt.ztzx.model.dto.system.SysUserDto;
import com.zt.ztzx.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysUserMapper {

    @Select("select * from sys_user where username=#{userName} and is_deleted=0")
    SysUser login(String userName);

    List<SysUser> findByPage(SysUserDto sysUserDto);
    SysUser findByUserName(String userName);

    void saveSysUser(SysUser sysUser);

    void updateSysUser(SysUser sysUser);

    void deleteById(Long userId);

}
