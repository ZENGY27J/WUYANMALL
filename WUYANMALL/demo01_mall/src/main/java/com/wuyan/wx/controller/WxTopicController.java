package com.wuyan.wx.controller;

import com.wuyan.mall.bean.Topic;
import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.mall.vo.PageInfo;
import com.wuyan.wx.bean.Databean;
import com.wuyan.wx.bean.TopicBean;
import com.wuyan.wx.service.topicService.WxTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    @RequestMapping("detail")
    public BaseRespVo getTopicDetail(int id){
        TopicBean topicBean = wxTopicService.getTopicDetail(id);
        return BaseRespVo.ok(topicBean);
    }
    @RequestMapping("related")
    public BaseRespVo getTopicRelated(int id){
        List<Topic> topic = wxTopicService.getTopic(id);
        return BaseRespVo.ok(topic);
    }
}
