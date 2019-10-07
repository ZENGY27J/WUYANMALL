package com.wuyan.wx.vo;

import com.wuyan.mall.bean.Comment;

import java.util.List;

public class CommentVo {
    long count;
    List<SingleCommentVo> data;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<SingleCommentVo> getData() {
        return data;
    }

    public void setData(List<SingleCommentVo> data) {
        this.data = data;
    }
}
