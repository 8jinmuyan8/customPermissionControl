package com.lx.project.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lx.project.BizException;
import com.lx.project.base.CurrentUser;
import com.lx.project.base.CurrentUserContext;
import com.lx.project.domain.SysUserSignModel;
import com.lx.project.domain.UserRoleBean;
import com.lx.project.entity.SysMenu;
import com.lx.project.entity.SysUser;
import com.lx.project.entity.SysUserRole;
import com.lx.project.service.ISysUserRoleService;
import com.lx.project.service.ISysUserService;
import com.lx.project.utils.DateUtils;
import com.lx.project.utils.JSONModel;
import com.lx.project.utils.WebUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private ISysUserRoleService userRoleService;

    @GetMapping("/info")
    public JSONModel info() {
        CurrentUser currentUser = CurrentUserContext.get();
        UserRoleBean user = sysUserService.info(currentUser.getId());
        return JSONModel.buildSuccess("ok", user);
    }

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
       sysUserService.signOut(currentUser);
        return JSONModel.buildSuccess("退出成功");
    }

    @GetMapping("/menu")
    public JSONModel menu() {
        CurrentUser currentUser = CurrentUserContext.get();
        List<SysMenu> list = sysUserService.listMenuByUserId(currentUser.getId());

        return JSONModel.buildSuccess("ok", list);
    }

    @PostMapping("/list")
    public JSONModel list(HttpServletRequest request) {
        CurrentUser currentUser = CurrentUserContext.get();
        int page = 0;
        int pageSize = 10;

        try {
            page = getInt(request, "page");
            pageSize = getInt(request, "pageSize");
        } catch (Exception e) {
            e.printStackTrace();
        }


        String name = getString(request, "name");

        IPage<UserRoleBean> result = sysUserService.getUserList(new Page<>(page,pageSize),name,currentUser.getId());


        Map<String, Object> data = new HashMap<>();
        data.put("list", result.getRecords());
        data.put("total", result.getTotal());
        return JSONModel.buildSuccess("ok", data);
    }

    @PostMapping("/save")
    public JSONModel save(HttpServletRequest request) {
        CurrentUser currentUser = new CurrentUser();
        int sex = 0;
        int id = 0 ;

        String executeStatus = null;
        String roleID = null;

        String account = getNotBlankString(request,"account","账户名不为空");
        String name = getNotBlankString(request,"name","姓名不为空");
        String password = getNotBlankString(request,"password","密码不为空");
        String email = getString(request,"email");
        String mobile = getString(request,"mobile");
        String birthday = getString(request,"birthday");
        roleID = getString(request,"roleID");

        String string_id = request.getParameter("id");
        try {
            sex = getInt(request,"sex");
            if (!string_id.isEmpty()){
                id = Integer.parseInt(string_id);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //新增用户必须选择角色
        if (id == 0)
        {
            roleID = getNotBlankString(request,"roleID","请选择用户角色");
        }

        SysUser sysUser = new SysUser();
        SysUserRole userRole =new SysUserRole();
        sysUser.setId(id);
        sysUser.setName(name);
        sysUser.setPassword(password);
        sysUser.setEmail(email);
        sysUser.setSex(sex);
        sysUser.setBirthday(birthday);
        sysUser.setDomainName(currentUser.getDomainName());

        userRole.setSysRoleId(Integer.parseInt(roleID));
        userRole.setSysUserId(id);

        if (id == 0) {
            //新增用户逻辑
            boolean execute = sysUserService.save(sysUser);
            userRole.setSysUserId(sysUser.getId());
            //关联角色用户表
            boolean executeRole = userRoleService.save(userRole);

            if (!execute && !executeRole ){
                throw new BizException("新增用户失败！");
            }
            executeStatus = "新增用户成功！";

        }else {
            //编辑用户逻辑
            boolean execute = sysUserService.updateById(sysUser);
            if (userRole.getSysRoleId() != null){
                userRoleService.updateByUserId(userRole.getSysRoleId(),userRole.getSysUserId());
            }


            if (!execute){
                throw new BizException("编辑用户失败！");
            }
            executeStatus = "编辑用户成功！";
        }

        return JSONModel.buildSuccess(executeStatus);
    }

    @PostMapping("/delete")
    public JSONModel userDelete(HttpServletRequest request){

        int id = 0;

        try {
            id = getInt(request,"id");
        } catch (Exception e) {
            e.printStackTrace();
        }
        sysUserService.deleteUserById(id);

        return JSONModel.buildSuccess("删除数据成功");
    }

    @PostMapping("/updatePassword")
    public JSONModel updatePassword(HttpServletRequest request) {
        String oldPwd = getNotBlankString(request, "oldPwd", "原密码不能为空");
        String newPwd = getNotBlankString(request, "newPwd", "新密码不能为空");
        CurrentUser currentUser = CurrentUserContext.get();

        sysUserService.updatePassword(currentUser.getId(), oldPwd, newPwd);

        return JSONModel.buildSuccess("修改成功，请重新登录");

    }
}
