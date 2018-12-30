package com.lx.project.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lx.project.base.CurrentUser;
import com.lx.project.base.CurrentUserContext;
import com.lx.project.entity.SysAccessLog;
import com.lx.project.service.ISysAccessLogService;
import com.lx.project.utils.JSONModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sys/access/log")
public class SysAccessLogController extends BaseController {

    @Autowired
    private ISysAccessLogService sysAccessLogService;

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


        String account = getString(request, "account");

        IPage<SysAccessLog> result = sysAccessLogService.getAccessLogList(new Page<>(page,pageSize),account);

        Map<String, Object> data = new HashMap<>();
        data.put("list", result.getRecords());
        data.put("total", result.getTotal());

        return JSONModel.buildSuccess("ok", data);
    }
}
