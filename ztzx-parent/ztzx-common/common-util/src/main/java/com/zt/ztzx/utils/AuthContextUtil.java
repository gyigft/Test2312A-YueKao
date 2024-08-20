package com.zt.ztzx.utils;

import com.zt.ztzx.model.entity.system.SysUser;

/**
 * @Author: 天山一剑卓不凡
 */
//完成线程和当前用户信息做绑定，用户每次请求之后会去获取当前用户信息，从token解析完之后获取
    //保证每次请求开辟的线程是当前用户所独享的，同其他的线程做数据隔离
public class AuthContextUtil {
    private static ThreadLocal<SysUser> threadLocal = new ThreadLocal<>();
    //线程绑定
    public static void setUser(SysUser user) {
        threadLocal.set(user);
    }
    public static SysUser getUser() {
        return threadLocal.get();
    }
    public static void remove() {
        threadLocal.remove();
    }
}
