package com.app.shiroweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * createBy: yezi
 * createAt: 2018/8/26 下午4:11
 * describe:
 **/
public class User {
    private Long uid;   //用户id

    private String username; //登录名，不可改，唯一
    private String nickname; //昵称，可改，唯一
    private String password; //已加密的登录密码
    private String salt; //加盐密值
    private Date createTime; //创建时间
    private Date updateTime; //修改时间
    //用户所有角色值，用于shiro 做角色权限的判断
    private Set<String> roles = new HashSet<String>();

    //用户所有权限值，用于shiro做资源权限的判断
    private Set<String> permissions = new HashSet<String>();

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", roles=" + roles +
                ", permissions=" + permissions +
                '}';
    }
}
