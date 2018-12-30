package com.lx.project.service;

import com.lx.project.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 系统菜单 服务类
 * </p>
 *
 * @author LX
 * @since 2018-12-23
 */
public interface ISysMenuService extends IService<SysMenu> {
    List<SysMenu>  getMenuList();

    void saveMenu( SysMenu sysMenu);

    void del(String domainName, Integer id);
}
