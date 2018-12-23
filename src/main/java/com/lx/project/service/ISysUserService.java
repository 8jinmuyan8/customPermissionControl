package com.lx.project.service;

import com.lx.project.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author LX
 * @since 2018-12-23
 */
public interface ISysUserService extends IService<SysUser> {
    SysUser selectByAccount( String account);
}
