package com.wuyan.wx.bean;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Program: WUYANMALL
 * @Author: ZyEthan
 * @Description: 用户订单详细信息
 * @Date: 2019-10-05-20:07
 */
public class UserOrderInfo {
    // 订单实际价格
    BigDecimal actualPrice;
    // 订单包含的商品集合
    List<UserGoodsInfo> goodsList;
    // 操作选项
    HandleOption handleOption;
    // 订单id
    int id;
    // 是否为团购活动
    Boolean isGroupin;
    // 订单编号
    String orderSn;
    // 订单状态描述
    String orderStatusText;

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public List<UserGoodsInfo> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<UserGoodsInfo> goodsList) {
        this.goodsList = goodsList;
    }

    public HandleOption getHandleOption() {
        return handleOption;
    }

    public void setHandleOption(HandleOption handleOption) {
        this.handleOption = handleOption;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getGroupin() {
        return isGroupin;
    }

    public void setGroupin(Boolean groupin) {
        isGroupin = groupin;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getOrderStatusText() {
        return orderStatusText;
    }

    public void setOrderStatusText(String orderStatusText) {
        this.orderStatusText = orderStatusText;
    }
}
