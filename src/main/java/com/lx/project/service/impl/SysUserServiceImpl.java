package com.lx.project.service.impl;

import com.lx.project.entity.SysUser;
import com.lx.project.mapper.SysUserMapper;
import com.lx.project.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author LX
 * @since 2018-12-23
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Autowired
    SysUserMapper userMapper;
    @Override
    public SysUser selectByAccount(String account) {
        return userMapper.selectByAccount(account);
    }
}
