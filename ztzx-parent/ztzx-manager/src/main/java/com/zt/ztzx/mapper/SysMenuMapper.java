package com.zt.ztzx.mapper;

import com.zt.ztzx.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: 天山一剑卓不凡
 */
public interface SysMenuMapper {

    @Select("select * from sys_menu where is_deleted =0")
    List<SysMenu> findAll();
    @Select("select * from sys_menu where is_deleted =0 and parent_id =#{parentId}")
    List<SysMenu> findAllByParentId(Long parentId);

    void insert(SysMenu sysMenu);

    void updateById(SysMenu sysMenu);
    public abstract void deleteById(Long id);
    int countByParentId(Long id);
}
