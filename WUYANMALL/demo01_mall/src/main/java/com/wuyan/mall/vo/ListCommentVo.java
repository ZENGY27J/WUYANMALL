package com.wuyan.mall.vo;

import com.wuyan.mall.bean.Comment;

import java.util.List;

public class ListCommentVo {
    long total;
    List<Comment> items;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<Comment> getItems() {
        return items;
    }

    public void setItems(List<Comment> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "CommentVo{" +
                "total=" + total +
                ", items=" + items +
                '}';
    }
}
