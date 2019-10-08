package com.wuyan.mall.shiro;

import com.wuyan.mall.mapper.UserMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Program: WUYANMALL
 * @Author: ZyEthan
 * @Description: 微信端，在Realm中获取认证和授权信息
 * @Date: 2019-10-08-11:12
 */
@Component
public class WxRealm extends AuthorizingRealm{
    @Autowired
    UserMapper userMapper;
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 通过subject执行login获得usernamePasswordToken中的username
        String principal = (String) authenticationToken.getPrincipal();
        // 根据principal（用户名）去数据库查询对应的密码
        String password = userMapper.queryPasswordByUsername(principal);
        // 判断list是否为空，若为空，n那么密码错误
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal,password,this.getName());
        return authenticationInfo;
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
