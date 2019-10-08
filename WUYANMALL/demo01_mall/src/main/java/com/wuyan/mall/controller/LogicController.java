package com.wuyan.mall.controller;

import com.wuyan.mall.bean.AdminExample;
import com.wuyan.mall.bean.Permission;
import com.wuyan.mall.bean.PermissionExample;
import com.wuyan.mall.bean.Role;
import com.wuyan.mall.bean.permission.AdminPermInfo;
import com.wuyan.mall.mapper.AdminMapper;
import com.wuyan.mall.mapper.PermissionMapper;
import com.wuyan.mall.mapper.RoleMapper;
import com.wuyan.mall.service.AdminpremInfo.AdminpremInfoService;
import com.wuyan.mall.shiro.CustomToken;
import com.wuyan.mall.vo.AdminInfo;
import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.mall.vo.LoginVo;
import com.wuyan.mall.vo.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Program: WUYANMALL
 * @Author: ZyEthan
 * @Description: 控制层
 * @Date: 2019-09-30-11:53
 */
@RestController
public class LogicController {

    @Autowired
    AdminpremInfoService adminpremInfoService;
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    PermissionMapper permissionMapper;

    @RequestMapping("admin/auth/login")
    public BaseRespVo logic(@RequestBody LoginVo loginVo) {
        CustomToken token = new CustomToken(loginVo.getUsername(),loginVo.getPassword(),"admin");
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);

        } catch (AuthenticationException e) {
            return BaseRespVo.fail();
        }
        Serializable id = subject.getSession().getId();
        BaseRespVo ok = BaseRespVo.ok(id);
        return ok;
    }
    @RequestMapping("admin/auth/info")
    public BaseRespVo info(String token) {
        Subject subject = SecurityUtils.getSubject();
        String principal = (String) subject.getPrincipal();
//        AdminPermInfo adminPermInfo = adminpremInfoService.getPermInfo(principal);
//        adminPermInfo.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        UserInfo userInfo = new UserInfo();
        userInfo.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        userInfo.setName(principal);
        PermissionExample permissionExample = new PermissionExample();
        List<Permission> permissions = permissionMapper.selectByExample(permissionExample);
        // 查询该用户角色编码
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andUsernameEqualTo(principal);
        List<AdminInfo> admins = adminMapper.selectByExample(adminExample);
        AdminInfo admin = admins.get(0);
        Integer[] roleIds = admin.getRoleIds();
        List<String> roleList = new ArrayList<>();
        for (Integer roleId : roleIds) {
            Role role = roleMapper.selectByPrimaryKey(roleId);
            String name = role.getName();
            roleList.add(name);
        }
        // 根据roleId得到权限
        List<String> permissionList = new ArrayList<>();
        for (Integer roleId : roleIds) {
            for (Permission permission : permissions) {
                if (roleId == permission.getRoleId()) {
                    permissionList.add(permission.getPermission());
                }
            }
        }
        userInfo.setPerms(permissionList);
        userInfo.setRoles(roleList);
        BaseRespVo ok = BaseRespVo.ok(userInfo);
        return ok;
    }
    @RequestMapping("admin/auth/logout")
//    @RequiresPermissions("*")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "admin/auth/login";
    }
    @RequestMapping("/fail")
    public String fail() {
        return "fail.....zy";
    }
    @RequestMapping("/fail123")
    public String fail123() {
        return "fail.....zy11112341231";
    }
}
