package com.app.test;

import com.app.config.MyShiroRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class CustomRealmTest {

    @Test
    public void testCustomRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();

        // 1.构建securityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(myShiroRealm);

        //使用加密之后的认证
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);

        myShiroRealm.setCredentialsMatcher(matcher);

        // 2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("tom","123");
        try {
            subject.login(token);
        } catch (UnknownAccountException ex) {
            System.out.println("subject.isAuthenticated: 用户不存在" + subject.isAuthenticated());
        } catch (IncorrectCredentialsException ex) {
            System.out.println("subject.isAuthenticated: 密码不匹配" + subject.isAuthenticated());
        } catch (Exception ex) {
            System.out.println("subject.isAuthenticated: 后台错误" + subject.isAuthenticated());
        }

        //subject.checkRole("admin");
        //subject.checkPermissions("user:add","user:delete");

    }
}
