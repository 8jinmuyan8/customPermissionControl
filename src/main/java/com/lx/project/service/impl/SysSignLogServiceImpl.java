package com.lx.project.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lx.project.entity.SysSignLog;
import com.lx.project.mapper.SysSignLogMapper;
import com.lx.project.service.ISysSignLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统登录日志 服务实现类
 * </p>
 *
 * @author LX
 * @since 2018-12-23
 */
@Service
public class SysSignLogServiceImpl extends ServiceImpl<SysSignLogMapper, SysSignLog> implements ISysSignLogService {
    @Autowired
    SysSignLogMapper signLogMapper;
    @Override
    public IPage<SysSignLog> getSignLogList(Page page, String account) {
        return signLogMapper.getSignLogList(page,account);
    }
}
