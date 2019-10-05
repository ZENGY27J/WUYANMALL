package com.wuyan.wx.service.topicService;

import com.github.pagehelper.PageHelper;
import com.wuyan.mall.bean.Topic;
import com.wuyan.mall.bean.TopicExample;
import com.wuyan.mall.mapper.TopicMapper;
import com.wuyan.mall.vo.PageInfo;
import com.wuyan.wx.bean.Databean;
import com.wuyan.wx.utils.QueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxTopicServiceImpl implements WxTopicService {
    @Autowired
    TopicMapper topicMapper;
    @Override
    public Databean getTopicList(PageInfo pageInfo) {
        TopicExample example = new TopicExample();
        long l = topicMapper.countByExample(example);
        PageHelper.startPage(pageInfo.getPage(),pageInfo.getSize());
        List<Topic> topics = topicMapper.selectByExample(example);
        Databean databean = new Databean();
        databean.setCount(l);
        databean.setData(topics);
        return databean;
    }
}
