package com.lx.project.mapper;

import com.lx.project.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 系统菜单 Mapper 接口
 * </p>
 *
 * @author LX
 * @since 2018-12-23
 */
@Mapper
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 拿到菜单LIST
     */
    List<SysMenu>  getMenuList();
    /**
     * 检查有没有未删除的子菜单
     */
    Integer countParentId(@Param("parentId")Integer parentId);

}
