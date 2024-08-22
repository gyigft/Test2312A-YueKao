package com.zt.ztzx.service.impl;

import com.zt.ztzx.helper.MenuHelper;
import com.zt.ztzx.mapper.SysRoleMenuMapper;
import com.zt.ztzx.model.entity.system.SysMenu;
import com.zt.ztzx.model.entity.system.SysUser;
import com.zt.ztzx.model.vo.system.SysMenuVo;
import com.zt.ztzx.service.SysMenuService;
import com.zt.ztzx.service.SysRoleMenuService;
import com.zt.ztzx.utils.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author: 天山一剑卓不凡
 */
@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public Map<String, Object> findSysRoleMenuByRoleId(Long roleId) {

        // 查询所有的菜单数据
        List<SysMenu> sysMenuList = sysMenuService.findAll() ;

        // 查询当前角色的菜单数据
        List<Long> roleMenuIds = sysRoleMenuMapper.findSysRoleMenuByRoleId(roleId) ;

        // 将数据存储到Map中进行返回
        Map<String , Object> result = new HashMap<>() ;
        result.put("sysMenuList" , sysMenuList) ;
        result.put("roleMenuIds" , roleMenuIds) ;

        // 返回
        return result;
    }


    @Override
    public List<SysMenuVo> findSysMenus() {
        //获取当前的用户
        SysUser user = AuthContextUtil.getUser();
        Long id = user.getId();
        // 根据用户id查询菜单

        List<SysMenu> sysMenuList = sysRoleMenuMapper.findSysMenus(id) ;

        //构建树形数据
        List<SysMenu> sysMenuTreeList = MenuHelper.treeNode(sysMenuList);
        return this.buildMenus(sysMenuTreeList);
    }
    // 将List<SysMenu>对象转换成List<SysMenuVo>对象
    private List<SysMenuVo> buildMenus(List<SysMenu> menus) {

        List<SysMenuVo> sysMenuVoList = new LinkedList<SysMenuVo>();
        for (SysMenu sysMenu : menus) {
            SysMenuVo sysMenuVo = new SysMenuVo();
            sysMenuVo.setTitle(sysMenu.getTitle());
            sysMenuVo.setName(sysMenu.getComponent());
            List<SysMenu> children = sysMenu.getChildren();
            if (!CollectionUtils.isEmpty(children)) {
                sysMenuVo.setChildren(buildMenus(children));
            }
            sysMenuVoList.add(sysMenuVo);
        }
        return sysMenuVoList;
    }
}
