package com.wuyan.mall.controller;

import com.wuyan.mall.bean.*;
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
    AdminService adminService;

    @Autowired
    LogService logService;

    @Autowired
    RoleService roleService;

    @Autowired
    StorageService storageService;

    @RequestMapping("admin/admin/list")
    public BaseRespVo adminList(PageInfo pageInfo, String username){
        System.out.println(pageInfo.getLimit());
        System.out.println(username);

        List<AdminInfo> admins = adminService.selectByExample(username);

        ResultInfos results = new ResultInfos();
        results.setTotal(admins.size());
        results.setItems(admins);

        BaseRespVo ok = BaseRespVo.ok(results);
        return ok;
    }

    @RequestMapping("admin/role/options")
    public BaseRespVo roleOptions(){
        RoleInfo role = new RoleInfo();
        role.setValue(12);
        role.setLabel("奥特曼");

        List<RoleInfo> roles = new ArrayList<>();
        roles.add(role);

        BaseRespVo ok = BaseRespVo.ok(roles);
        return ok;
    }

    @RequestMapping("admin/log/list")
    public BaseRespVo logList(PageInfo pageInfo, String name){

        List<Log> logs = logService.selectByExample(name);

        ResultInfos results = new ResultInfos();
        results.setTotal(logs.size());
        results.setItems(logs);

        BaseRespVo ok = BaseRespVo.ok(results);
        return ok;
    }

    /**
     *
     * @param pageInfo
     * @return
     */
    @RequestMapping("admin/role/list")
    public BaseRespVo roleList(PageInfo pageInfo, String name){
        //System.out.println(pageInfo.getLimit());
        RoleExample roleExample = new RoleExample();
        List<Role> roles = roleService.selectByExample(name);

        //封装
        ResultInfos results = new ResultInfos();
        results.setTotal(roles.size());
        results.setItems(roles);
        //返回
        BaseRespVo ok = BaseRespVo.ok(results);
        return ok;
    }

    /**
     * 获得对象
     * @param pageInfo
     * @return
     */
    @RequestMapping("admin/storage/list")
    public BaseRespVo storageList(PageInfo pageInfo,String key,String name){
        List<Storage> storages = storageService.selectByExample(key, name);
        //封装
        ResultInfos results = new ResultInfos();
        results.setTotal(storages.size());
        results.setItems(storages);
        //返回
        BaseRespVo ok = BaseRespVo.ok(results);
        return ok;
    }

    @RequestMapping("admin/role/create")
    public BaseRespVo roleCreate(@RequestBody RoleVo roleVo){
        System.out.println(roleVo.getDesc());
        System.out.println(roleVo.getName());

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
}
