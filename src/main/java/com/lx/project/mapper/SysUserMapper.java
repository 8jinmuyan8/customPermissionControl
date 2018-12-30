package com.lx.project.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lx.project.base.CurrentUser;
import com.lx.project.domain.SysUserSignModel;
import com.lx.project.domain.UserRoleBean;
import com.lx.project.entity.SysMenu;
import com.lx.project.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author LX
 * @since 2018-12-23
 */
@Mapper
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 通过name查询ID
     */
    SysUser selectByAccount(@Param("account") String account);
    /**
     * 通过token确定当前用户
     */
    CurrentUser queryByAccessToken(@Param("token") String token);
    /**
     * 登录返回token给用户
     */
    SysUserSignModel signIn(@Param("account") String username, @Param("password") String password);
    /**
     * 退出登录
     */
    void signOut(@Param("userId")int userId);
    /**
     * 根据userID查出menu
     */
    List<SysMenu > listMenuByUserId(@Param("userId")int userId);
    /**
     * 自定义分页
     */
    IPage<SysUser> getList(Page page, @Param("acount") String acount,@Param("id") Integer id);

    /**
     * 自定义分页 含有角色的连表查询
     */
    IPage<UserRoleBean> getUserList(Page page, @Param("acount") String acount, @Param("id") Integer id);
    /**
     * 物理删除 通过ID
     */
    Boolean deleteUserById(@Param("userId") Integer userId);
    /**
     * 通过ID查询个人信息
     */
    UserRoleBean info(@Param("userId") Integer userId);
    /**
     * 检验密码是否正确
     */
    Boolean updateCheckPassword(@Param("sysUserId") Integer sysUserId, @Param("oldPwd") String oldPwd);
}
