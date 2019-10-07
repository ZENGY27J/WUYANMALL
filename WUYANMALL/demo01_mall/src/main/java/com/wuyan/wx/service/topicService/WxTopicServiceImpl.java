package com.wuyan.wx.service.topicService;

import com.github.pagehelper.PageHelper;
import com.wuyan.mall.bean.Goods;
import com.wuyan.mall.bean.Topic;
import com.wuyan.mall.bean.TopicExample;
import com.wuyan.mall.mapper.GoodsMapper;
import com.wuyan.mall.mapper.TopicMapper;
import com.wuyan.mall.vo.PageInfo;
import com.wuyan.wx.bean.Databean;
import com.wuyan.wx.bean.TopicBean;
import com.wuyan.wx.utils.QueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WxTopicServiceImpl implements WxTopicService {
    @Autowired
    TopicMapper topicMapper;
    @Autowired
    GoodsMapper goodsMapper;
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

    @Override
    public TopicBean getTopicDetail(int id) {
        List<Topic> topics = topicMapper.selectByExample(QueryUtils.getTopicById(id));
        Topic topic = topics.get(0);
        String[] goods = topic.getGoods();
        List<Goods> goodsList = new ArrayList<>();
        for (String good : goods) {
            if (good != null && !"".equals(good)) {
                List<Goods> goodsList1 = goodsMapper.selectByExample(QueryUtils.getGoodsById(Integer.parseInt(good)));
                goodsList.add(goodsList1.get(0));
            }
        }
        return new TopicBean(goodsList,topic);
    }

    @Override
    public List<Topic> getTopic(int id) {
        List<Topic> topics = topicMapper.selectByExample(new TopicExample());
        List<Topic> tops = new ArrayList<>();
        for (Topic topic : topics) {
            if (topic.getId() != id){
                tops.add(topic);
            }
            if (tops.size() >= 4){
                break;
            }
        }
        return tops;
    }
}
