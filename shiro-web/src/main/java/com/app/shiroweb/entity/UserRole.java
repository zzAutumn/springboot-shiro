package com.app.shiroweb.entity;

import java.io.Serializable;

/**
 * createBy: yezi
 * createAt: 2018/8/26 下午4:38
 * describe: 用户角色关系
 **/
public class UserRole implements Serializable {

    private Long userId;
    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
