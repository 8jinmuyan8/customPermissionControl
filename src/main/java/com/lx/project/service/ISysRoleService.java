package com.lx.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lx.project.domain.UserRoleList;
import com.lx.project.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;



import java.util.List;

/**
 * <p>
 * 系统角色 服务类
 * </p>
 *
 * @author LX
 * @since 2018-12-23
 */

public interface ISysRoleService extends IService<SysRole> {
    List<UserRoleList> getAllUserRoleList();
    /**
     * 角色分页查询
     */
    IPage<SysRole> selectRoleList(Page page, String  rname);
    /**
     * 根据id删除角色
     */
    void deleteRoleByRid(Integer roleID);
    /**
     * 通过ID拿到菜单
     */
    List<Integer> listMenuByRoleId( Integer roleId);

    /**
     *菜单权限保存
     * @param domainName
     * @param roleId
     * @param menuIds
     */
    void authMenus(String domainName, int roleId, List<Integer> menuIds);
}
