package com.wuyan.mall.vo;

import com.wuyan.mall.bean.Goods;

import java.util.List;

public class ListGoodsVo<T> {
    //商品总数
   long total;
   List items;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }
}
