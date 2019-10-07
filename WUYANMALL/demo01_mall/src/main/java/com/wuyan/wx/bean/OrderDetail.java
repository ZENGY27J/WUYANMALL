package com.wuyan.wx.bean;

import com.wuyan.mall.bean.Order;
import com.wuyan.mall.bean.OrderGoods;

import java.util.List;

/**
 * @Program: WUYANMALL
 * @Author: ZyEthan
 * @Description: 订单商品详情
 * @Date: 2019-10-06-16:10
 */
public class OrderDetail {
    List<OrderGoods> orderGoods;
    Order orderInfo;

    public List<OrderGoods> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(List<OrderGoods> orderGoods) {
        this.orderGoods = orderGoods;
    }

    public Order getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(Order orderInfo) {
        this.orderInfo = orderInfo;
    }
}
