package com.lx.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 用户角色
 * </p>
 *
 * @author LX
 * @since 2018-12-23
 */
@TableName("tb_sys_user_role")
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Integer sysUserId;

    /**
     * 角色ID
     */
    private Integer sysRoleId;

    /**
     * 操作人
     */
    private String domainName;

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

    public Integer getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Integer sysUserId) {
        this.sysUserId = sysUserId;
    }
    public Integer getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(Integer sysRoleId) {
        this.sysRoleId = sysRoleId;
    }
    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
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
        return "SysUserRole{" +
        "sysUserId=" + sysUserId +
        ", sysRoleId=" + sysRoleId +
        ", domainName=" + domainName +
        ", isactive=" + isactive +
        ", inserttime=" + inserttime +
        ", updatetime=" + updatetime +
        "}";
    }
}
