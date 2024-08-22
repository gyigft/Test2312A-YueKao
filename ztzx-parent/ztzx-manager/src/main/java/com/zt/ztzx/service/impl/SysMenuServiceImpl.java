package com.zt.ztzx.service.impl;

import com.zt.ztzx.exception.ZhentaoException;
import com.zt.ztzx.helper.MenuHelper;
import com.zt.ztzx.mapper.SysMenuMapper;
import com.zt.ztzx.model.entity.system.SysMenu;
import com.zt.ztzx.model.vo.common.ResultCodeEnum;
import com.zt.ztzx.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 天山一剑卓不凡
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    //渲染的返回的是一级菜单，一级菜单的子菜单同时查询出来
    public List<SysMenu> findAll() {
        //所有的菜单
        List<SysMenu> all = sysMenuMapper.findAll();
        //数据健壮性
        if (CollectionUtils.isEmpty(all)){
            return null;
        }
     /*   //查询一级菜单
        List<SysMenu> menuList = new ArrayList<>();
        //遍历添加一级菜单
        all.forEach(o -> {
            if (o.getParentId().longValue() == 0l) {
                menuList.add(MenuHelper.treeNode(all));
            }
        });*/
        List<SysMenu> menus = MenuHelper.treeNode(all);

        return menus;
    }

    @Override
    public List<SysMenu> findMenus(Long parentId) {

        List<SysMenu> byParentId = sysMenuMapper.findAllByParentId(parentId);

        //查询一级菜单
        byParentId.forEach(o -> {
            /*  if (o.getParentId().longValue()==o.getId()) {*/
            o.setChildren(findMenus(o.getId()));
//            }
        });

        return byParentId;
    }

    @Override
    public void save(SysMenu sysMenu) {
        sysMenuMapper.insert(sysMenu) ;
    }

    @Override
    public void updateById(SysMenu sysMenu) {
        sysMenuMapper.updateById(sysMenu);
    }

    @Override
    public void removeById(Long id) {
        int count = sysMenuMapper.countByParentId(id);  // 先查询是否存在子菜单，如果存在不允许进行删除
        if (count > 0) {
            throw new ZhentaoException(ResultCodeEnum.NODE_ERROR);
        }
        sysMenuMapper.deleteById(id);		// 不存在子菜单直接删除
    }
}
