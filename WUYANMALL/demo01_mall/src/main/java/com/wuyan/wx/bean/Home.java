package com.wuyan.wx.bean;

import com.wuyan.mall.bean.*;

import java.util.List;

public class Home {
    private List<Advertisement> banner;

    private List<Brand> brandList;

    private List<Category> channel;

    private List<Coupon> couponList;

    private List<Category> floorGoodsList;

    private List<GrouponList> grouponList;

    private List<Goods> hotGoodsList;

    private List<Goods> newGoodsList;

    private List<Topic> topicList;

    public List<Advertisement> getBanner() {
        return banner;
    }

    public void setBanner(List<Advertisement> banner) {
        this.banner = banner;
    }

    public List<Brand> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<Brand> brandList) {
        this.brandList = brandList;
    }

    public List<Category> getChannel() {
        return channel;
    }

    public void setChannel(List<Category> channel) {
        this.channel = channel;
    }

    public List<Coupon> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<Coupon> couponList) {
        this.couponList = couponList;
    }

    public List<Category> getFloorGoodsList() {
        return floorGoodsList;
    }

    public void setFloorGoodsList(List<Category> floorGoodsList) {
        this.floorGoodsList = floorGoodsList;
    }

    public List<GrouponList> getGrouponList() {
        return grouponList;
    }

    public void setGrouponList(List<GrouponList> grouponList) {
        this.grouponList = grouponList;
    }

    public List<Goods> getHotGoodsList() {
        return hotGoodsList;
    }

    public void setHotGoodsList(List<Goods> hotGoodsList) {
        this.hotGoodsList = hotGoodsList;
    }

    public List<Goods> getNewGoodsList() {
        return newGoodsList;
    }

    public void setNewGoodsList(List<Goods> newGoodsList) {
        this.newGoodsList = newGoodsList;
    }

    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }
}
