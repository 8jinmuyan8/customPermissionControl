package com.lx.project.service;

import com.lx.project.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户角色 服务类
 * </p>
 *
 * @author LX
 * @since 2018-12-23
 */
public interface ISysUserRoleService extends IService<SysUserRole> {
    void updateByUserId(Integer roleId,Integer userId);
}
