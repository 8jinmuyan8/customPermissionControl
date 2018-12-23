package com.lx.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 系统菜单
 * </p>
 *
 * @author LX
 * @since 2018-12-23
 */
@TableName("tb_sys_menu")
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String name;

    /**
     * 图标
     */
    private String icon;

    /**
     * 路径
     */
    private String path;

    /**
     * 上级菜单
     */
    private Integer parentId;

    /**
     * 顺序
     */
    private Integer seq;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
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
        return "SysMenu{" +
        "name=" + name +
        ", icon=" + icon +
        ", path=" + path +
        ", parentId=" + parentId +
        ", seq=" + seq +
        ", domainName=" + domainName +
        ", isactive=" + isactive +
        ", inserttime=" + inserttime +
        ", updatetime=" + updatetime +
        "}";
    }
}
