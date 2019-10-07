package com.wuyan.mall.service.AdminpremInfo.impl;

import com.wuyan.mall.bean.*;
import com.wuyan.mall.bean.permission.AdminPermInfo;
import com.wuyan.mall.mapper.AdminMapper;
import com.wuyan.mall.mapper.PermissionMapper;
import com.wuyan.mall.mapper.RoleMapper;
import com.wuyan.mall.service.AdminpremInfo.AdminpremInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Program: WUYANMALL
 * @Author: ZyEthan
 * @Description:
 * @Date: 2019-10-04-21:45
 */
@Service
public class AdminpremInfoServiceImpl implements AdminpremInfoService {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    PermissionMapper permissionMapper;
    @Override
    public AdminPermInfo getPermInfo(String principal) {
        AdminPermInfo adminPermInfo = new AdminPermInfo();
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andUsernameEqualTo(principal);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        Admin admin = admins.get(0);
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria roleExampleCriteria = roleExample.createCriteria();
        PermissionExample permissionExample = new PermissionExample();
        PermissionExample.Criteria permissionExampleCriteria = permissionExample.createCriteria();
        List<String> permissions = null;
        List<String> roles = null;
        String[] roleIds = admin.getRoleIds();
        for (int i = 0; i <roleIds.length ; i++) {
            Integer roleId = Integer.valueOf(roleIds[i]);
            Role role = roleMapper.selectByPrimaryKey(roleId);
            roles.add(role.getName());
            permissionExampleCriteria.andRoleIdEqualTo(Integer.valueOf(roleIds[i]));
            List<Permission> permissionList = permissionMapper.selectByExample(permissionExample);
            if (permissionList != null) {
                for (Permission permission : permissionList) {
                    if (!permissions.contains(permission.getPermission())) {
                        permissions.add(permission.getPermission());
                    }
                }
            }
        }
        adminPermInfo.setName(principal);
        adminPermInfo.setRoles(roles);
        adminPermInfo.setPerms(permissions);
        return adminPermInfo;
    }
}
