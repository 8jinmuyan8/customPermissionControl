package com.lx.project.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lx.project.BizException;
import com.lx.project.domain.UserRoleList;
import com.lx.project.entity.SysRole;
import com.lx.project.entity.SysRoleMenu;
import com.lx.project.mapper.SysRoleMapper;
import com.lx.project.mapper.SysRoleMenuMapper;
import com.lx.project.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统角色 服务实现类
 * </p>
 *
 * @author LX
 * @since 2018-12-23
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
    Logger log = LoggerFactory.getLogger(this.getClass());
   @Autowired
   SysRoleMapper roleMapper;

    @Autowired
    SysRoleMenuMapper roleMenuMapper;

    @Override
    public List<UserRoleList> getAllUserRoleList() {
        return roleMapper.getAllUserRoleList();
    }

    @Override
    public IPage<SysRole> selectRoleList(Page page, String rname) {
        return roleMapper.selectRoleList(page,rname);
    }

    @Override
    public void deleteRoleByRid(Integer roleID) {
        boolean execute = roleMapper.deleteRoleByRid(roleID);
        if (!execute){
            throw new BizException("删除数据失败！");
        }

    }

    @Override
    public List<Integer> listMenuByRoleId(Integer roleId) {
        return roleMapper.listMenuByRoleId(roleId);
    }

    @Override
    public void authMenus(String domainName, int roleId, List<Integer> menuIds) {
        // 先将之前的全部删除
    roleMenuMapper.deleteSysRoleMenuById(domainName,roleId);

        // 添加菜单
     SysRoleMenu roleMenu = new SysRoleMenu();
     roleMenu.setDomainName(domainName);
     roleMenu.setSysRoleId(roleId);
        for (Integer menuId : menuIds) {
            roleMenu.setSysMenuId(menuId);
            roleMenuMapper.insert(roleMenu);
        }
    }


}
