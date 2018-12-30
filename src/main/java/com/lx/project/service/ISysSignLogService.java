package com.lx.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lx.project.entity.SysSignLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统登录日志 服务类
 * </p>
 *
 * @author LX
 * @since 2018-12-23
 */
public interface ISysSignLogService extends IService<SysSignLog> {
    /**
     * 自定义分页
     */
    IPage<SysSignLog> getSignLogList(Page page, String account);
}
