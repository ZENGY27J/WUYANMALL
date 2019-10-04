package com.wuyan.mall.vo;

import java.util.List;

public class ResultInfos {
    private long total;
    private List items;

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
