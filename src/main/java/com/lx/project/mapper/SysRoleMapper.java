package com.lx.project.mapper;

import com.lx.project.domain.UserRoleList;
import com.lx.project.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 系统角色 Mapper 接口
 * </p>
 *
 * @author LX
 * @since 2018-12-23
 */
@Mapper
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<UserRoleList> getAllUserRoleList();
}
