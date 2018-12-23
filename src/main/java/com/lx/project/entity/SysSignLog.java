package com.lx.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 系统登录日志
 * </p>
 *
 * @author LX
 * @since 2018-12-23
 */
@TableName("tb_sys_sign_log")
public class SysSignLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * IP地址
     */
    private String ipAddr;

    /**
     * UA
     */
    private String userAgent;

    /**
     * 是否成功
     */
    private Integer status;

    /**
     * 删除标志
     */
    private Boolean isactive;

    /**
     * 创建时间
     */
    private LocalDateTime inserttime;

    /**
     * 修改时间
     */
    private LocalDateTime updatetime;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }
    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }
    public LocalDateTime getInserttime() {
        return inserttime;
    }

    public void setInserttime(LocalDateTime inserttime) {
        this.inserttime = inserttime;
    }
    public LocalDateTime getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(LocalDateTime updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return "SysSignLog{" +
        "username=" + username +
        ", ipAddr=" + ipAddr +
        ", userAgent=" + userAgent +
        ", status=" + status +
        ", isactive=" + isactive +
        ", inserttime=" + inserttime +
        ", updatetime=" + updatetime +
        "}";
    }
}
