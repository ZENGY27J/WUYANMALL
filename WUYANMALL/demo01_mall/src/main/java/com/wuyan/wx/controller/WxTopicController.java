package com.wuyan.wx.controller;

import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.mall.vo.PageInfo;
import com.wuyan.wx.bean.Databean;
import com.wuyan.wx.service.topicService.WxTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wx/topic")
public class WxTopicController {
    @Autowired
    WxTopicService wxTopicService;
    @RequestMapping("list")
    public BaseRespVo getTopicList(PageInfo pageInfo){
        Databean databean = wxTopicService.getTopicList(pageInfo);
        return BaseRespVo.ok(databean);
    }
}
