package com.wuyan.mall.bean;

import java.util.List;

/**
 * @Program: WUYANMALL
 * @Author: ZyEthan
 * @Description: 基本Data泛型类
 * @Date: 2019-09-30-21:46
 */
public class BaseData<T> {
    long total;

    List<T> items;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
