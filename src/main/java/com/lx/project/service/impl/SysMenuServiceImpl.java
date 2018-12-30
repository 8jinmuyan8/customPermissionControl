package com.lx.project.service.impl;

import com.lx.project.BizException;
import com.lx.project.entity.SysMenu;
import com.lx.project.mapper.SysMenuMapper;
import com.lx.project.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统菜单 服务实现类
 * </p>
 *
 * @author LX
 * @since 2018-12-23
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
    @Autowired
    SysMenuMapper menuMapper;

    @Override
    public List<SysMenu> getMenuList() {
        List<SysMenu> list = menuMapper.getMenuList();
        return list;
    }

    @Override
    public void saveMenu( SysMenu sysMenu) {
        if (null == sysMenu.getId()){
            menuMapper.insert(sysMenu);
        }else {
            menuMapper.updateById(sysMenu);
        }
    }

    @Override
    public void del(String domainName, Integer id) {
        int count = menuMapper.countParentId(id);
        if (0 < count) {
            throw new BizException("当前菜单存在子菜单，删除失败");
        }

        SysMenu sysMenu = new SysMenu();
        sysMenu.setId(id);
        sysMenu.setIsactive(0);
        sysMenu.setDomainName(domainName);
        int execute =  menuMapper.updateById(sysMenu);
        if (1 != execute) {
            throw new BizException();
        }
    }
}
