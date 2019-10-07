package com.wuyan.mall.shiro;

import com.wuyan.mall.bean.*;
import com.wuyan.mall.mapper.AdminMapper;
import com.wuyan.mall.mapper.PermissionMapper;
import com.wuyan.mall.mapper.RoleMapper;
import com.wuyan.mall.vo.AdminInfo;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @Program: WUYANMALL
 * @Author: ZyEthan
 * @Description: 自定义的Realm，在Realm中获取认证和授权信息
 * @Date: 2019-10-03-20:10
 */
@Component
public class CustomRealm extends AuthorizingRealm {

    // 在数据查询信息匹配 执行认证和授权
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    PermissionMapper permissionMapper;

    /**
     * 认证操作 获得数据库中的用户信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 通过subject执行login获得usernamePasswordToken中的username
        String principal = (String) authenticationToken.getPrincipal();
        // 根据principal（用户名）去数据库查询对应的密码
        String password = adminMapper.queryPasswordByUsername(principal);
        // 判断list是否为空，若为空，n那么密码错误
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal,password,"CustomRealm");
        return authenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        // 去数据库查询权限信息
//        // 取admin中的role_ids信息
//        AdminExample adminExample = new AdminExample();
//        AdminExample.Criteria criteria = adminExample.createCriteria();
//        criteria.andUsernameEqualTo(primaryPrincipal);
//        List<Admin> admins = adminMapper.selectByExample(adminExample);
//        Admin admin = admins.get(0);
//        RoleExample roleExample = new RoleExample();
//        RoleExample.Criteria roleExampleCriteria = roleExample.createCriteria();
//        PermissionExample permissionExample = new PermissionExample();
//        PermissionExample.Criteria permissionExampleCriteria = permissionExample.createCriteria();
//        List<String> permissions = null;
//        List<String> roles = null;
//        String[] roleIds = admin.getRoleIds();
////        for (String roleId : roleIds) {
////            Role role = roleMapper.selectByPrimaryKey(Integer.valueOf(roleId));
////            roles.add(role.getName());
////        }
//        for (int i = 0; i <roleIds.length ; i++) {
//            Integer roleId = Integer.valueOf(roleIds[i]);
//            Role role = roleMapper.selectByPrimaryKey(roleId);
//            roles.add(role.getName());
//            permissionExampleCriteria.andRoleIdEqualTo(Integer.valueOf(roleIds[i]));
//            List<Permission> permissionList = permissionMapper.selectByExample(permissionExample);
//            if (permissionList != null) {
//                for (Permission permission : permissionList) {
//                    if (!permissions.contains(permission.getPermission())) {
//                        permissions.add(permission.getPermission());
//                    }
//                }
//            }
//        }
//        authorizationInfo.addStringPermissions(permissions);
//        authorizationInfo.addRoles(roles);
//        return authorizationInfo;
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        authorizationInfo.addStringPermission("user:insert");
//        authorizationInfo.addStringPermission("user:delete");
//        authorizationInfo.addStringPermission("user:update");
//        authorizationInfo.addStringPermission("user:query");

        //权限信息时根据认证时的用户信息去数据库中查询获得的
//        List<String> permissions = new ArrayList<>();
//        permissions.add("user:query");
//        permissions.add("user:insert");
//        permissions.add("user:delete");
//        permissions.add("user:update");
//        permissions.add("user:query");
        List<String> permissions = adminMapper.queryPermissionsByUsername(primaryPrincipal);
        authorizationInfo.addStringPermissions(permissions);
        List<String> roles = null;
//        String[] roleIds = admin.getRoleIds();
//        for (String roleId : roleIds) {
//            Role role = roleMapper.selectByPrimaryKey(Integer.valueOf(roleId));
//            roles.add(role.getName());
//        }
//        authorizationInfo.addRoles(roles);
        return authorizationInfo;
    }
}
