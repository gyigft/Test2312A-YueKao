package com.zt.ztzx.mapper;

import com.zt.ztzx.model.dto.system.LoginDto;
import com.zt.ztzx.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Select;

public interface SysUserMapper {

    @Select("select * from sys_user where username=#{userName} and is_deleted=0")
    SysUser login(String userName);

}
