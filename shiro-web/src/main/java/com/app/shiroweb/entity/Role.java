package com.app.shiroweb.entity;

import java.util.Date;

/**
 * createBy: yezi
 * createAt: 2018/8/26 下午4:28
 * describe: 用户角色
 **/
public class Role {
    private Long roleId; //角色id
    private String roleName; //角色名称
    private String roleDescribe; //角色描述
    private String roleVal; //角色值，用于权限判断
    private Date createTime; //创建时间
    private Date updateTime; //修改时间

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescribe() {
        return roleDescribe;
    }

    public void setRoleDescribe(String roleDescribe) {
        this.roleDescribe = roleDescribe;
    }

    public String getRoleVal() {
        return roleVal;
    }

    public void setRoleVal(String roleVal) {
        this.roleVal = roleVal;
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
