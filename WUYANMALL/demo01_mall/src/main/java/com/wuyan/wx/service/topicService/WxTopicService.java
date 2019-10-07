package com.wuyan.wx.service.topicService;

import com.wuyan.mall.vo.PageInfo;
import com.wuyan.wx.bean.Databean;

public interface WxTopicService {
    Databean getTopicList(PageInfo pageInfo);
}
