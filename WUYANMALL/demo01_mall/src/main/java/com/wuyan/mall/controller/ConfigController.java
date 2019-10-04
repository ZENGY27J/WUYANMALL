package com.wuyan.mall.controller;

import com.wuyan.mall.bean.System;
import com.wuyan.mall.service.configService.ConfigService;
import com.wuyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin/config")
public class ConfigController {

    @Autowired
    ConfigService ConfigService;

    @RequestMapping(value = "mall", method = RequestMethod.GET)
    public BaseRespVo mallConfig() {
        BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();

        //将查询结果封装在 QueryMap 中
        Map QueryMap = ConfigService.queryMallConfig();

        objectBaseRespVo.setErrno(0);
        objectBaseRespVo.setData(QueryMap);
        objectBaseRespVo.setErrmsg("成功");

        return objectBaseRespVo;
    }

    @RequestMapping(value = "mall", method = RequestMethod.POST)
    public BaseRespVo mallConfig(@RequestBody Map<String, String> map) {
        BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();

        ConfigService.updateConfig(map);
        objectBaseRespVo.setErrmsg("成功");
        objectBaseRespVo.setErrno(0);
        return objectBaseRespVo;
    }

    @RequestMapping(value = "express", method = RequestMethod.GET)
    public BaseRespVo expressConfig() {
        BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();

        //将查询结果封装在 QueryMap 中
        Map QueryMap = ConfigService.queryExpressConfig();

        objectBaseRespVo.setErrno(0);
        objectBaseRespVo.setData(QueryMap);
        objectBaseRespVo.setErrmsg("成功");

        return objectBaseRespVo;
    }

    @RequestMapping(value = "express", method = RequestMethod.POST)
    public BaseRespVo expressConfig(@RequestBody Map<String, String> map) {
        BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();

        ConfigService.updateConfig(map);
        objectBaseRespVo.setErrmsg("成功");
        objectBaseRespVo.setErrno(0);
        return objectBaseRespVo;
    }


    @RequestMapping(value = "order", method = RequestMethod.GET)
    public BaseRespVo orderConfig() {
        BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();

        //将查询结果封装在 QueryMap 中
        Map QueryMap = ConfigService.queryOrderConfig();

        objectBaseRespVo.setErrno(0);
        objectBaseRespVo.setData(QueryMap);
        objectBaseRespVo.setErrmsg("成功");

        return objectBaseRespVo;
    }

    @RequestMapping(value = "order", method = RequestMethod.POST)
    public BaseRespVo orderConfig(@RequestBody Map<String, String> map) {
        BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();

        ConfigService.updateConfig(map);
        objectBaseRespVo.setErrmsg("成功");
        objectBaseRespVo.setErrno(0);
        return objectBaseRespVo;
    }

    @RequestMapping(value = "wx", method = RequestMethod.GET)
    public BaseRespVo wxConfig() {
        BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();

        //将查询结果封装在 QueryMap 中
        Map QueryMap = ConfigService.queryWxConfig();

        objectBaseRespVo.setErrno(0);
        objectBaseRespVo.setData(QueryMap);
        objectBaseRespVo.setErrmsg("成功");

        return objectBaseRespVo;
    }

    @RequestMapping(value = "wx", method = RequestMethod.POST)
    public BaseRespVo wxConfig(@RequestBody Map<String, String> map) {
        BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();

        ConfigService.updateConfig(map);
        objectBaseRespVo.setErrmsg("成功");
        objectBaseRespVo.setErrno(0);
        return objectBaseRespVo;
    }


}
