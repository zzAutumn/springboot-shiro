package com.app.shiroweb.entity;

import java.util.Date;

/**
 * createBy: yezi
 * createAt: 2018/8/26 下午4:31
 * describe: 权限实体类
 **/
public class Permission {
    /**
     * 权限类型: 菜单
     */
    public static final int PTYPE_MENU = 1;

    /**
     * 权限类型： 按钮
     */
    public static final int PTYPE_BUTTON = 2;

    private Long pid; //权限id
    private String pname; //权限名称
    private Integer ptype; //权限类型
    private String pval; //权限值，shiro 的权限控制表达式
    private Date createTime; //创建时间
    private Date updateTime; //修改时间

    public static int getPtypeMenu() {
        return PTYPE_MENU;
    }

    public static int getPtypeButton() {
        return PTYPE_BUTTON;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getPtype() {
        return ptype;
    }

    public void setPtype(Integer ptype) {
        this.ptype = ptype;
    }

    public String getPval() {
        return pval;
    }

    public void setPval(String pval) {
        this.pval = pval;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
