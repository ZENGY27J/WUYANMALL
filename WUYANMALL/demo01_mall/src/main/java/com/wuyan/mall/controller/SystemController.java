package com.wuyan.mall.controller;

import com.wuyan.mall.bean.*;
import com.wuyan.mall.mapper.RoleMapper;
import com.wuyan.mall.service.systemService.AdminService;
import com.wuyan.mall.service.systemService.LogService;
import com.wuyan.mall.service.systemService.RoleService;
import com.wuyan.mall.service.systemService.StorageService;
import com.wuyan.mall.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.System;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class SystemController {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    AdminService adminService;

    @Autowired
    LogService logService;

    @Autowired
    RoleService roleService;

    @Autowired
    StorageService storageService;

    /**
     * 管理员信息
     * @param pageInfo
     * @param username
     * @return
     */
    @RequestMapping("admin/admin/list")
    public BaseRespVo adminList(PageInfo pageInfo, String username){
        //System.out.println(pageInfo.getLimit());
        //System.out.println(username);

        ResultInfos results = adminService.selectByExample(pageInfo.getPage(), pageInfo.getLimit(), username);

        BaseRespVo ok = BaseRespVo.ok(results);
        return ok;
    }

    /**
     * 增加管理员
     * @return
     */
    @RequestMapping("admin/admin/create")
    public BaseRespVo adminCreate(@RequestBody Admin admin){
        admin.setAddTime(new Date());
        admin.setUpdateTime(new Date());

        int flag = adminService.insertSelective(admin);
        if(flag != 0){
            Admin adminToVo = adminService.selectByPrimaryKey(admin.getId());
            BaseRespVo ok = BaseRespVo.ok(adminToVo);
            return ok;
        }
        return null;
    }

    /**
     * 修改管理员信息
     * @return
     */
    @RequestMapping("admin/admin/update")
    public BaseRespVo adminUpdate(@RequestBody Admin admin){
        int flag = adminService.updateByPrimaryKeySelective(admin);

        if(flag != 0){
            Admin adminToVo = adminService.selectByPrimaryKey(admin.getId());
            BaseRespVo ok = BaseRespVo.ok(adminToVo);
            return ok;
        }
        return null;
    }

    /**
     * 删除管理员
     * @param admin
     * @return
     */
    @RequestMapping("admin/admin/delete")
    public BaseRespVo adminDelete(@RequestBody Admin admin){
        adminService.deleteByPrimaryKey(admin.getId());
        return BaseRespVo.ok(null);
    }

    @RequestMapping("admin/role/options")
    public BaseRespVo roleOptions(){


        RoleExample example = new RoleExample();
        List<Role> roles = roleMapper.selectByExample(example);

        List<RoleInfo> rolesToVo = new ArrayList<>();
        for (int i = 0; i < roles.size(); i++) {
            RoleInfo role = new RoleInfo();
            role.setValue(roles.get(i).getId());
            role.setLabel(roles.get(i).getName());
            rolesToVo.add(role);
        }

        BaseRespVo ok = BaseRespVo.ok(rolesToVo);
        return ok;
    }

    /**
     * 获得日志信息
     * @param pageInfo
     * @param name
     * @return
     */
    @RequestMapping("admin/log/list")
    public BaseRespVo logList(PageInfo pageInfo, String name){

        ResultInfos results = logService.selectByExample(pageInfo.getPage(), pageInfo.getLimit(), name);

        BaseRespVo ok = BaseRespVo.ok(results);
        return ok;
    }

    /**
     * 获取角色信息
     * @param pageInfo
     * @return
     */
    @RequestMapping("admin/role/list")
    public BaseRespVo roleList(PageInfo pageInfo, String name){
        //System.out.println(pageInfo.getLimit());
        ResultInfos results = roleService.selectByExample(pageInfo.getPage(), pageInfo.getLimit(), name);

        //返回
        BaseRespVo ok = BaseRespVo.ok(results);
        return ok;
    }

    /**
     * 增加角色信息
     * @param roleVo
     * @return
     */
    @RequestMapping("admin/role/create")
    public BaseRespVo roleCreate(@RequestBody RoleVo roleVo){
        /*System.out.println(roleVo.getDesc());
        System.out.println(roleVo.getName());*/

        Role role = new Role();
        role.setName(roleVo.getName());
        role.setDesc(roleVo.getDesc());
        role.setAddTime(new Date());
        role.setUpdateTime(new Date());
        int flag = roleService.insertSelective(role);
        //System.out.println(flag);

        if(flag != 0){
            Role roleToVo = roleService.selectByPrimaryKey(role.getId());
            BaseRespVo ok = BaseRespVo.ok(roleToVo);
            return ok;
        }
        return null;
    }

    /**
     * 修改角色信息
     * @param role
     * @return
     */
    @RequestMapping("admin/role/update")
    public BaseRespVo roleUpdate(@RequestBody Role role){
        roleService.updateByPrimaryKeySelective(role);
        return BaseRespVo.ok(null);
    }

    /**
     * 删除角色信息
     * @param role
     * @return
     */
    @RequestMapping("admin/role/delete")
    public BaseRespVo roleDelete(@RequestBody Role role){
        roleService.deleteByPrimaryKey(role.getId());
        return BaseRespVo.ok(null);
    }

    /**
     * 获得对象
     * @param pageInfo
     * @return
     */
    @RequestMapping("admin/storage/list")
    public BaseRespVo storageList(PageInfo pageInfo,String key,String name){
        ResultInfos results = storageService.selectByExample(pageInfo.getPage(), pageInfo.getLimit(), key, name);

        //返回
        BaseRespVo ok = BaseRespVo.ok(results);
        return ok;
    }

    /**
     * 修改对象信息
     * @param storage
     * @return
     */
    @RequestMapping("admin/storage/update")
    public BaseRespVo storageUpdate(@RequestBody Storage storage){
        storageService.updateByPrimaryKeySelective(storage);

        Storage storageToVo = storageService.selectByPrimaryKey(storage.getId());

        return BaseRespVo.ok(storageToVo);
    }

    /**
     * 删除对象信息
     * @param storage
     * @return
     */
    @RequestMapping("admin/storage/delete")
    public BaseRespVo storageDelete(@RequestBody Storage storage){
        storageService.deleteByPrimaryKey(storage.getId());
        return BaseRespVo.ok(null);
    }
}
