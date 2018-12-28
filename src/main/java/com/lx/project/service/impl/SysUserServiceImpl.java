package com.lx.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lx.project.BizException;
import com.lx.project.base.CurrentUser;
import com.lx.project.domain.SysUserSignModel;
import com.lx.project.domain.UserRoleBean;
import com.lx.project.entity.SysMenu;
import com.lx.project.entity.SysSignLog;
import com.lx.project.entity.SysUser;
import com.lx.project.mapper.SysSignLogMapper;
import com.lx.project.mapper.SysUserMapper;
import com.lx.project.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.project.utils.DateUtils;
import com.lx.project.utils.IDUtils;
import com.lx.project.utils.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.Date;
import java.util.List;

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
    private static Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);
    @Autowired
    SysUserMapper userMapper;
    @Autowired
    SysSignLogMapper sysSignLogMapper;

    @Override
    public SysUser selectByAccount(String account) {
        return userMapper.selectByAccount(account);
    }

    @Override
    public CurrentUser queryByAccessToken(String token) {
        return userMapper.queryByAccessToken(token);
    }

    @Override
    public SysUserSignModel signIn(String username, String password, String ipAddr, String userAegnt) {
        SysSignLog signLog = new SysSignLog();
        if (StringUtils.isNotBlank(userAegnt) && 200 < userAegnt.length()) {
            userAegnt = userAegnt.substring(0, 200);
        }
        signLog.setUserAgent(userAegnt);
        signLog.setIpAddr(ipAddr);
        signLog.setUsername(username);
        sysSignLogMapper.insert(signLog);

        SysUserSignModel signModel = userMapper.signIn(username,password);
        if (null == signModel) {
            log.error("请输入正确账号和密码：{}", username);
            throw new BizException("请输入正确账号和密码");
        }
        // 设置登录成功
        signLog.setStatus(1);
        sysSignLogMapper.update(signLog,null);

        String creatToken = username +";"+ System.currentTimeMillis();
        creatToken = TokenUtils.encrypt(creatToken);
        SysUser sysUser = new SysUser();
        sysUser.setId(signModel.getId());
        sysUser.setAccessToken(creatToken);
        signModel.setToken(creatToken);
        userMapper.updateById(sysUser) ;

        return signModel;
    }

    @Override
    public void signOut(CurrentUser currentUser) {
        int userId = currentUser.getId();
          userMapper.signOut(userId);

    }

    @Override
    public List<SysMenu> listMenuByUserId(int userId) {
        return userMapper.listMenuByUserId(userId);
    }

    @Override
    public IPage<SysUser> getList(Page page, String name, Integer id) {
        return userMapper.getList(page,name,id);
    }

    @Override
    public IPage<UserRoleBean> getUserList(Page page, String acount, Integer id) {
        return userMapper.getUserList(page,acount,id);
    }
}
