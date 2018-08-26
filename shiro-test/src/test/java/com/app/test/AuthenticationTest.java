package com.app.test;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

public class AuthenticationTest {

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void addUser() {
        simpleAccountRealm.addAccount("tom", "123","admin","user");
    }


    @Test
    public void testAuthentication() {
        // 1.构建securityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);

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

        subject.checkRoles("admin","user");

        subject.logout();
    }
}
