package com.lx.project.mapper;

import com.lx.project.entity.SysRoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 角色对应菜单 Mapper 接口
 * </p>
 *
 * @author LX
 * @since 2018-12-23
 */
@Mapper
@Repository
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {
    /**
     * 删除 角色和菜单间的关联
     */
    Boolean deleteSysRoleMenuById (@Param("domainName")String domainName,@Param("roleId")Integer roleId);
}
