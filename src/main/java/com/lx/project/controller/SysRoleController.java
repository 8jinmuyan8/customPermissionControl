package com.lx.project.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lx.project.BizException;
import com.lx.project.base.CurrentUser;
import com.lx.project.base.CurrentUserContext;
import com.lx.project.domain.UserRoleList;
import com.lx.project.entity.SysRole;
import com.lx.project.service.ISysRoleService;
import com.lx.project.utils.JSONModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
public class SysRoleController extends BaseController {
    @Autowired
    ISysRoleService roleService;

    @GetMapping("/list")
    public JSONModel allUserRoleList() {

        List<UserRoleList> list = roleService.getAllUserRoleList();
        Map<String, Object> data = new HashMap<>();
        data.put("list", list);

        return JSONModel.buildSuccess("ok",data);
    }

    @PostMapping("/list")
    public JSONModel roleList(HttpServletRequest request) {
        int page = 0;
        int pageSize = 10;

        try {
            page = getInt(request, "page");
            pageSize = getInt(request, "pageSize");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String name = getString(request, "rname");

        IPage<SysRole> result = roleService.selectRoleList(new Page<>(page, pageSize), name);


        Map<String, Object> data = new HashMap<>();
        data.put("list", result.getRecords());
        data.put("total", result.getTotal());

        return JSONModel.buildSuccess("ok",data);
    }

    @PostMapping("/delete")
    public JSONModel userDelete(HttpServletRequest request){

        String roleID = getNotBlankString(request,"roleID","删除数据失败！");
        roleService.deleteRoleByRid(Integer.parseInt(roleID));

        return JSONModel.buildSuccess("删除数据成功！");
    }

    @PostMapping("/save")
    public JSONModel roleSave(HttpServletRequest request) {
        String executeStatus = null;
        String id = getString(request,"id");

        String rname = getNotBlankString(request,"rname","角色名不为空");
        String description = getString(request,"description");


        SysRole sysRole = new SysRole();
        sysRole.setRname(rname);
        sysRole.setDescription(description);

        if (StringUtils.isNotBlank(id)) {
            sysRole.setId(Integer.parseInt(id));
       boolean execute = roleService.updateById(sysRole);

        if (!execute){
                throw new BizException("编辑角色失败！");
            }
            executeStatus = "编辑角色成功！";

        }else {
            boolean execute = roleService.save(sysRole);

            if (!execute){
                throw new BizException("新增角色失败！");
            }
            executeStatus = "新增角色成功！";
        }

        return JSONModel.buildSuccess(executeStatus);
    }

    @GetMapping("/menuIds")
    public JSONModel menuIds(@RequestParam("roleID") int roleID) {
        List<Integer> list = roleService.listMenuByRoleId(roleID);
        return JSONModel.buildSuccess("ok", list);
    }

    @PostMapping("/authMenus")
    public JSONModel authMenus(@RequestParam("roleID") int roleId, @RequestParam("menuIds") String ids) {
        List<Integer> menuIds = new ArrayList<>();

        CurrentUser currentUser = CurrentUserContext.get();
        String domainName = currentUser.getDomainName();

        if (StringUtils.isNotBlank(ids)) {
            String[] arr = ids.split(",");
            for (String id : arr) {
                menuIds.add(Integer.parseInt(id));
            }
        }


        roleService.authMenus(domainName, roleId, menuIds);
        return JSONModel.buildSuccess("操作成功");
    }

}
