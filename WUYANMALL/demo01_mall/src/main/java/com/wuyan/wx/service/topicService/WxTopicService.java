package com.wuyan.wx.service.topicService;

import com.wuyan.mall.bean.Topic;
import com.wuyan.mall.vo.PageInfo;
import com.wuyan.wx.bean.Databean;
import com.wuyan.wx.bean.TopicBean;

import java.util.List;

public interface WxTopicService {
    Databean getTopicList(PageInfo pageInfo);

    TopicBean getTopicDetail(int id);

    List<Topic> getTopic(int id);
}
