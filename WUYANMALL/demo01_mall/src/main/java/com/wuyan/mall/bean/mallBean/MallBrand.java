package com.wuyan.mall.bean.mallBean;

import com.wuyan.mall.bean.Brand;

import java.util.List;

public class MallBrand {
    long total;
    List<Brand> items;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<Brand> getItems() {
        return items;
    }

    public void setItems(List<Brand> items) {
        this.items = items;
    }
}
