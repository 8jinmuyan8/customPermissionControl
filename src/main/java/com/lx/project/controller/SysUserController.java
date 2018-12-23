package com.lx.project.controller;


import com.lx.project.BizException;
import com.lx.project.base.CurrentUser;
import com.lx.project.base.CurrentUserContext;
import com.lx.project.domain.SysUserSignModel;
import com.lx.project.service.ISysUserService;
import com.lx.project.utils.JSONModel;
import com.lx.project.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author LX
 * @since 2018-12-23
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends BaseController{

    @Autowired
    private ISysUserService sysUserService;
    @PostMapping("/signIn")
    public JSONModel signIn(HttpServletRequest request) {

        String username = getNotBlankString(request, "username", "账号不能为空");
        String password = getNotBlankString(request, "password", "密码不能为空");

        String ipAddr = WebUtils.getIpAddr(request);
        String ua = request.getHeader("User-Agent");
        SysUserSignModel userSignModel  = sysUserService.signIn(username, password, ipAddr, ua);
        return JSONModel.buildSuccess("登录成功", userSignModel);
    }

    @GetMapping("/signOut")
    public JSONModel signOut() {
        CurrentUser currentUser = CurrentUserContext.get();
     //   sysUserService.signOut(currentUser);
        return JSONModel.buildSuccess("退出成功");
    }
}
