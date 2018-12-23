package com.lx.project.service;

import com.lx.project.entity.SysAccessLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDateTime;

/**
 * <p>
 * 系统访问日志 服务类
 * </p>
 *
 * @author LX
 * @since 2018-12-23
 */
public interface ISysAccessLogService extends IService<SysAccessLog> {
    int add(String url, String ipAddr, String method, String userAgent);

    void updateEndTime(LocalDateTime localDateTime,int id);

    void updateAccountById(int id, String account);
}
