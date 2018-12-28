package com.lx.project.service.impl;

import com.lx.project.domain.UserRoleList;
import com.lx.project.entity.SysRole;
import com.lx.project.mapper.SysRoleMapper;
import com.lx.project.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
   @Autowired
   SysRoleMapper roleMapper;
    @Override
    public List<UserRoleList> getAllUserRoleList() {
        return roleMapper.getAllUserRoleList();
    }
}
