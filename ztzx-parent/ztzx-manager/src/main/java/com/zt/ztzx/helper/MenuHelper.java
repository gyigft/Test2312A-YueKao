package com.zt.ztzx.helper;

import com.zt.ztzx.model.entity.system.SysMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 天山一剑卓不凡
 */
public class MenuHelper {


    //递归获取子节点
    //菜单树
    public static List<SysMenu> treeNode(List<SysMenu> list){
        List<SysMenu> menuList = new ArrayList<>();
        for (SysMenu sysMenu : list) {
            //父节点
            if(sysMenu.getParentId().longValue() == 0L){
                menuList.add(getChildList(list,sysMenu));
            }
        }
        return menuList;

    }
    //子菜单
    public static SysMenu getChildList(List<SysMenu> list, SysMenu sysMenu){
        sysMenu.setChildren(new ArrayList<>());
        //遍历集合过滤数据
        list.forEach(o->{
            //判断当钱菜单的父节点id是否等于父菜单的id
            if(o.getParentId().longValue()==sysMenu.getId().longValue()){
                sysMenu.getChildren().add(getChildList(list,o));
            }
        });



       /* List<SysMenu> sysMenuList = list.stream().filter(menu -> menu.getParentId().equals(sysMenu.getId())).collect(Collectors.toList());

        for (SysMenu menu : sysMenuList) {
            menu.setChildren(getChildList(list,menu));
        }

        return sysMenuList;*/
        return  sysMenu;
    }
}
