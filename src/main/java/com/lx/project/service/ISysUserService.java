package com.lx.project.service;

import com.lx.project.base.CurrentUser;
import com.lx.project.domain.SysUserSignModel;
import com.lx.project.entity.SysMenu;
import com.lx.project.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author LX
 * @since 2018-12-23
 */
public interface ISysUserService extends IService<SysUser> {
    /**
     * 通过name查询ID
     */
    SysUser selectByAccount( String account);
    /**
     * 通过token确定当前用户
     */
    CurrentUser queryByAccessToken( String token);
    /**
     * 登录返回token id给用户
     */
    SysUserSignModel signIn(String username, String password, String ipAddr, String userAegnt);
    /**
     * 退出登录
     */
    void signOut(CurrentUser currentUser);

    /**
     * 根据userID查出menu
     */
    List<SysMenu > listMenuByUserId(int userId);
}
