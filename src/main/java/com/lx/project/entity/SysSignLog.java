package com.lx.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

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
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
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
    private Integer isactive;

    /**
     * 创建时间
     */
    private Date inserttime;

    /**
     * 修改时间
     */
    private Date updatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setInserttime(Date inserttime) {
        this.inserttime = inserttime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

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

    public Integer getIsactive() {
        return isactive;
    }

    public void setIsactive(Integer isactive) {
        this.isactive = isactive;
    }

    public Date getInserttime() {
        return inserttime;
    }

    public Date getUpdatetime() {
        return updatetime;
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
