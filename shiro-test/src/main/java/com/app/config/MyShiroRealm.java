package com.app.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MyShiroRealm extends AuthorizingRealm {

    Map<String, String> userMap = new HashMap<String, String>();
    {
        userMap.put("tom", "5caf72868c94f184650f43413092e82c");
        super.setName("customRealm");
    }

    /**
     * 验证身份信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("---------------------------->验证身份信息");
        //1.从主体传过来的认证信息中，获得主体名字
        String username = (String) authenticationToken.getPrincipal();
        //2.通过用户名到数据库中获取凭证
        String password = getPasswordByUsername(username);
        //如果密码不存在说明用户不存在，直接返回null
        if (password == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                "tom",
                password,
                "customRealm"
        );
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(username));
        return authenticationInfo;
    }

    /**
     * 身份授权
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("---------------------->身份授权");
        //1.从认证信息中获取用户名
        String username =(String) principalCollection.getPrimaryPrincipal();
        //2.通过用户获取角色数据  从数据库或者缓存中获取角色数据
        Set<String> roles = getRolesByUsername(username);
        //3.获取权限数据
        Set<String> permissions = getPermissionsByUsername(username);

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setStringPermissions(permissions);
        authorizationInfo.setRoles(roles);

        return authorizationInfo;
    }

    /**
     * 查询权限数据
     * @param username
     * @return
     */
    private Set<String> getPermissionsByUsername(String username) {
        Set<String> sets = new HashSet<String>();
        sets.add("user:delete");
        sets.add("user:add");
        return sets;
    }

    /**
     * 模拟数据库查询凭证
     * @param username
     * @return
     */
    private String getPasswordByUsername(String username) {

        return userMap.get(username);
    }

    /**
     * 模拟数据库查询用户角色信息
     * @param username
     * @return
     */
    private Set<String> getRolesByUsername(String username) {
        Set<String> sets = new HashSet<String>();
        sets.add("admin");
        sets.add("user");
        return sets;
    }

    public static void main(String[] args) {
        //加密加盐
        Md5Hash md5Hash = new Md5Hash("123","tom");
        System.out.println(md5Hash);
    }
}
