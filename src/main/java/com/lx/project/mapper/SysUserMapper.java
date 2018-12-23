package com.lx.project.mapper;

import com.lx.project.base.CurrentUser;
import com.lx.project.domain.SysUserSignModel;
import com.lx.project.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
}
