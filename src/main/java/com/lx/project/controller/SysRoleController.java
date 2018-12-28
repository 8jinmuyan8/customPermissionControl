package com.lx.project.controller;


import com.lx.project.domain.UserRoleList;
import com.lx.project.service.ISysRoleService;
import com.lx.project.utils.JSONModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统角色 前端控制器
 * </p>
 *
 * @author LX
 * @since 2018-12-23
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController {
    @Autowired
    ISysRoleService roleService;
    @GetMapping("/list")
    public JSONModel allUserRoleList() {

        List<UserRoleList> list = roleService.getAllUserRoleList();
        Map<String, Object> data = new HashMap<>();
        data.put("list", list);

        return JSONModel.buildSuccess("ok",data);
    }
}
