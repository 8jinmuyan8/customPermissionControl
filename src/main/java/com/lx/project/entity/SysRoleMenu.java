package com.lx.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 角色对应菜单
 * </p>
 *
 * @author LX
 * @since 2018-12-23
 */
@TableName("tb_sys_role_menu")
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Integer sysRoleId;

    /**
     * 菜单ID
     */
    private Integer sysMenuId;

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

    public Integer getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(Integer sysRoleId) {
        this.sysRoleId = sysRoleId;
    }
    public Integer getSysMenuId() {
        return sysMenuId;
    }

    public void setSysMenuId(Integer sysMenuId) {
        this.sysMenuId = sysMenuId;
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
        return "SysRoleMenu{" +
        "sysRoleId=" + sysRoleId +
        ", sysMenuId=" + sysMenuId +
        ", domainName=" + domainName +
        ", isactive=" + isactive +
        ", inserttime=" + inserttime +
        ", updatetime=" + updatetime +
        "}";
    }
}
