package com.zt.ztzx.service;

import com.zt.ztzx.exception.ZhentaoException;
import com.zt.ztzx.model.dto.system.LoginDto;
import com.zt.ztzx.model.entity.system.SysUser;
import com.zt.ztzx.model.vo.common.Result;
import com.zt.ztzx.model.vo.system.LoginVo;

public interface SysUserService {

    Result<LoginVo> login(LoginDto loginDto) ;

    Result<SysUser> getUserinfo(String token);
}
