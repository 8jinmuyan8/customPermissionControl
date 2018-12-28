package com.lx.project.service;

import com.lx.project.domain.UserRoleList;
import com.lx.project.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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
}
