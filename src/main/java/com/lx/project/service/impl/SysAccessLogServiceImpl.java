package com.lx.project.service.impl;

import com.lx.project.entity.SysAccessLog;
import com.lx.project.mapper.SysAccessLogMapper;
import com.lx.project.service.ISysAccessLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 系统访问日志 服务实现类
 * </p>
 *
 * @author LX
 * @since 2018-12-23
 */
@Service
public class SysAccessLogServiceImpl extends ServiceImpl<SysAccessLogMapper, SysAccessLog> implements ISysAccessLogService {
    private Logger log = LoggerFactory.getLogger(SysAccessLogServiceImpl.class);
    @Autowired
    SysAccessLogMapper accessLogMapper;


    @Override
    public int add(String url, String ipAddr, String method, String userAgent) {
        try {
            SysAccessLog log = new SysAccessLog();
            log.setIpAddr(ipAddr);
            log.setMethod(method);
            log.setUrl(url);

            if (StringUtils.isNotBlank(userAgent) && userAgent.length() > 300) {
                userAgent = userAgent.substring(0, 300);
            }

            log.setUserAgent(userAgent);
            log.setStartTime(LocalDateTime.now());
            accessLogMapper.insert(log);
            return log.getId();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return 0;
    }

    @Override
    public void updateEndTime(LocalDateTime localDateTime,int id) {
        accessLogMapper.updateEndTime(localDateTime,id);
    }

    @Override
    public void updateAccountById(int id, String account) {
        accessLogMapper.updateAccountById(id,account);
    }
}
