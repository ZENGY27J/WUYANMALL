package com.wuyan.wx.bean;

import com.wuyan.mall.bean.Goods;
import com.wuyan.mall.bean.Topic;

import java.util.List;

public class TopicBean {
    List<Goods> goods;
    Topic topic;

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public TopicBean(List<Goods> goods, Topic topic) {
        this.goods = goods;
        this.topic = topic;
    }
}
