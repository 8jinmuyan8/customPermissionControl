package com.lx.project.controller;


import com.lx.project.base.CurrentUser;
import com.lx.project.base.CurrentUserContext;
import com.lx.project.entity.SysMenu;
import com.lx.project.service.ISysMenuService;
import com.lx.project.utils.JSONModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 系统菜单 前端控制器
 * </p>
 *
 * @author LX
 * @since 2018-12-23
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseController {

    @Autowired
    ISysMenuService menuService;

    @GetMapping("/list")
    public JSONModel list() {
        List<SysMenu> list = menuService.getMenuList();
        return JSONModel.buildSuccess("ok", list);
    }

    @PostMapping("/save")
    public JSONModel save(HttpServletRequest request) {
        SysMenu model = new SysMenu();

        String name = getNotBlankString(request, "name", "请输入名称");
        model.setName(name.trim());

        String path = getNotBlankString(request, "path", "请输入路径");
        model.setPath(path.trim());

        int req = getInt(request, "seq", "请输入序号");
        model.setSeq(req);

        String id = getString(request, "id");
        if (StringUtils.isNotBlank(id)) {
            model.setId(Integer.parseInt(id));
        }

        String parentId = getString(request, "parentId");
        if (StringUtils.isNotBlank(parentId)) {
            model.setParentId(Integer.parseInt(parentId));
        }
        model.setIcon(getString(request, "icon"));

        CurrentUser currentUser = CurrentUserContext.get();
        String domainName = currentUser.getDomainName();
        model.setDomainName(domainName);

        menuService.saveMenu(model);
        return JSONModel.buildSuccess("操作成功");
    }

    @PostMapping("/del")
    public JSONModel del(@RequestParam("id") int id) {
        CurrentUser currentUser = CurrentUserContext.get();
        menuService.del(currentUser.getDomainName(), id);
        return JSONModel.buildSuccess("操作成功");
    }
}
