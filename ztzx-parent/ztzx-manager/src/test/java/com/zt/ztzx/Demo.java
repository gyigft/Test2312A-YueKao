package com.zt.ztzx;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;

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
 /*   @Autowired
    private RedisTemplate<String,String> stringRedisTemplate;
    @Test
    public void test1(){
        redisTemplate.opsForValue().set("name","aaaa");
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println(name);

    }*/
}
