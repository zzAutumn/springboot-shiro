package com.app.shiroweb.config;

import com.app.shiroweb.entity.User;
import com.app.shiroweb.service.PermService;
import com.app.shiroweb.service.RoleService;
import com.app.shiroweb.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * createBy: yezi
 * createAt: 2018/8/26 下午5:20
 * describe: 自定义了如何查询用户信息，如何查询用户的角色和权限，如何校验密码等逻辑
 **/
public class MyShiroRealm extends AuthorizingRealm {

    //告诉shiro如何根据获取到的用户信息中密码和盐值来校验密码
    {
        super.setName("MyShiroRealm");
        //设置用于匹配密码的CredentialsMatcher
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);
        this.setCredentialsMatcher(matcher);
    }

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PermService permService;

    /**
     * 定义如何获取用户的角色和权限逻辑，给shiro做权限判断
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("---------------------------->授权身份");
        //获取用户
        User user = (User) getAvailablePrincipal(principalCollection);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        System.out.println("----------->获取角色信息："+user.getRoles());
        System.out.println("----------->获取权限信息："+user.getPermissions());
        info.setRoles(user.getRoles());
        info.setStringPermissions(user.getPermissions());
        return info;
    }

    /**
     * 用户身份验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("---------------------------->验证身份信息");
        //1.从主体传过来的认证信息中，获得主体名字
        String username = (String) authenticationToken.getPrincipal();
        //2.从数据库中查询用户是否存在
        User user = userService.findUserByName(username);

        if (user == null){
            throw new UnknownAccountException("用户不存在");
        }
        //查询用户的角色和权限存到SimpleAuthenticationInfo中，这样在其他地方
        //SecurityUtils.getSubject().getPrincipal()就能拿到用户的所有信息，包括角色和权限
        Set<String> roles = roleService.findRolesByUsername(username);
        Set<String> perms = permService.findPermsByUsername(username);
        user.getRoles().addAll(roles);
        user.getPermissions().addAll(perms);

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                getName()
        );
        if (user.getSalt() != null) {
            info.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
        }
        return info;
    }
}
