package com.zt.ztzx.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zt.ztzx.exception.ZhentaoException;
import com.zt.ztzx.mapper.SysRoleUserMapper;
import com.zt.ztzx.mapper.SysUserMapper;
import com.zt.ztzx.model.dto.system.AssginRoleDto;
import com.zt.ztzx.model.dto.system.LoginDto;
import com.zt.ztzx.model.dto.system.SysUserDto;
import com.zt.ztzx.model.entity.system.SysUser;
import com.zt.ztzx.model.vo.common.Result;
import com.zt.ztzx.model.vo.common.ResultCodeEnum;
import com.zt.ztzx.model.vo.system.LoginVo;
import com.zt.ztzx.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 天山一剑卓不凡
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    /**
     * 登录流程
     * 1、数据校验
     * 2、数据库校验
     * 3、生成token保存到redis
     * 4、结果返回
     * @param loginDto
     * @return
     */
//    @SneakyThrows
    @Override
    public Result<LoginVo> login(LoginDto loginDto)  {
        //进行统一异常处理 ，自定义异常
        if (ObjectUtil.isEmpty(loginDto)){
//            throw  new RuntimeException("数据有误");
            throw new ZhentaoException(ResultCodeEnum.DATA_ERROR.getCode(),ResultCodeEnum.DATA_ERROR,ResultCodeEnum.DATA_ERROR.getMessage());
        }
        SysUser sysUser = sysUserMapper.login(loginDto.getUserName());
        //校验免密码
        String password = DigestUtils.md5DigestAsHex(loginDto.getPassword().getBytes());
        if (!StrUtil.equals(password,sysUser.getPassword())){
//            throw  new RuntimeException("密码错误");
            throw  new ZhentaoException(ResultCodeEnum.LOGIN_ERROR.getCode(),ResultCodeEnum.LOGIN_ERROR,ResultCodeEnum.LOGIN_ERROR.getMessage());
        }
        //生成token
        String token = IdUtil.simpleUUID();
        //保存到redis中
        redisTemplate.opsForValue().set("user:login:"+token, JSON.toJSONString(sysUser),30, TimeUnit.MINUTES);
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        loginVo.setRefresh_token("fgga");

        return Result.build(loginVo,200,"登录成功");
    }

    @Override
    public Result<SysUser> getUserinfo(String token) {
        //从redis中获取用户信息
        String str = redisTemplate.opsForValue().get("user:login:" + token);
        SysUser sysUser = JSON.parseObject(str, SysUser.class);
        return Result.build(sysUser,200,"获取用户信息成功");
    }

    /**
     * 退出登录,把token从redis中删除
     * @param token
     */
    @Override
    public void logout(String token) {
        redisTemplate.delete("user:login:"+token);

    }
    @Override
    public PageInfo<SysUser> findByPage(SysUserDto sysUserDto, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum , pageSize);
        List<SysUser> sysUserList = sysUserMapper.findByPage(sysUserDto) ;
        PageInfo pageInfo = new PageInfo(sysUserList) ;
        return pageInfo;
    }

    @Override
    public void saveSysUser(SysUser sysUser) {
        // 根据输入的用户名查询用户
        SysUser dbSysUser = sysUserMapper.findByUserName(sysUser.getUserName());
        if(dbSysUser != null) {
            throw new ZhentaoException(ResultCodeEnum.USER_NAME_IS_EXISTS) ;
        }

        // 对密码进行加密
        String password = sysUser.getPassword();
        String digestPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        sysUser.setPassword(digestPassword);
        sysUser.setStatus(0);
        sysUserMapper.saveSysUser(sysUser) ;
    }

    @Override
    public void updateSysUser(SysUser sysUser) {
        sysUserMapper.updateSysUser(sysUser);
    }

    @Override
    public void deleteById(Long userId) {
        sysUserMapper.deleteById(userId);
    }

    @Override
    public void doAssign(AssginRoleDto assginRoleDto) {
        //1 根据userId删除用户之前分配角色数据
        sysRoleUserMapper.deleteByUserId(assginRoleDto.getUserId());

        //TODO 为了测试，模拟异常
//        int a = 1/0;

        //2 重新分配新数据
        List<Long> roleIdList = assginRoleDto.getRoleIdList();
        //遍历得到每个角色id
        for(Long roleId:roleIdList) {
            sysRoleUserMapper.doAssign(assginRoleDto.getUserId(),roleId);
        }
    }
}
