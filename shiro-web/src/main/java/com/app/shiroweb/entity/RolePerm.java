package com.app.shiroweb.entity;

import java.io.Serializable;

/**
 * createBy: yezi
 * createAt: 2018/8/26 下午4:39
 * describe: 角色权限关系
 **/
public class RolePerm implements Serializable {
    private Long roleId;
    private Long permId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermId() {
        return permId;
    }

    public void setPermId(Long permId) {
        this.permId = permId;
    }
}
