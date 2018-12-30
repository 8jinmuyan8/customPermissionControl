package com.lx.project.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lx.project.domain.UserRoleList;
import com.lx.project.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import javafx.scene.control.Pagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
    /**
     * 角色分页查询
     */
    IPage<SysRole> selectRoleList(Page page, @Param("rname")String  rname);
    /**
     * 根据id删除角色
     */
   Boolean deleteRoleByRid(@Param("roleID")Integer roleID);
   /**
    * 通过ID拿到菜单
    */
   List<Integer> listMenuByRoleId(@Param("roleId") Integer roleId);
}
