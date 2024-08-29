package com.zt.ztzx;

import com.zt.ztzx.model.entity.system.SysMenu;
import com.zt.ztzx.service.SysMenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * @Author: 天山一剑卓不凡
 */
@SpringBootTest
public class Demo {
    @Test
    public void test(){
        System.out.println("hello world");
        //密码加密
        String s = DigestUtils.md5DigestAsHex(("123456"+"abc").getBytes());
        String s1 = DigestUtils.md5DigestAsHex(("123456"+"efg").getBytes());
        String s2 = DigestUtils.md5DigestAsHex("123456".getBytes());
        System.out.println(s);
        System.out.println(s1);
        System.out.println(s2);

    }
    @Autowired
    private SysMenuService service;
    @Test
    public void test2(){

        List<SysMenu> menus1 = service.findMenus(0l);

        List<SysMenu> menus = service.findAll();
        System.out.println(menus1);
        System.out.println(menus);
    }
 /*   @Autowired
    private RedisTemplate<String,String> stringRedisTemplate;
    @Test
    public void test1(){
        redisTemplate.opsForValue().set("name","aaaa");
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println(name);

    }*/
}
