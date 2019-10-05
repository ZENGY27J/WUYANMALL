package com.wuyan.wx.controller;

import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.mall.vo.PageInfo;
import com.wuyan.wx.bean.Databean;
import com.wuyan.wx.service.grouponService.WxGrouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wx/groupon")
public class GrouponController {
    @Autowired
    WxGrouponService grouponService;
    @RequestMapping("list")
    public BaseRespVo getGroupon(PageInfo pageInfo){
        Databean databean = grouponService.getGroupon(pageInfo);
        return BaseRespVo.ok(databean);
    }
}
