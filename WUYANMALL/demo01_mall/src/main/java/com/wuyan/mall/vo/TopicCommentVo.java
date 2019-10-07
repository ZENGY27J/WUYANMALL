package com.wuyan.mall.vo;

import java.util.List;

/**
 * @Description: 返回专题评论列表 的返回对象
 * @Author: fangbo
 * @Date: 2019/10/7
 */
public class TopicCommentVo {
    List<TopicCommentDataVo> data;

    int count;

    int currentPage;

    public List<TopicCommentDataVo> getData() {
        return data;
    }

    public void setData(List<TopicCommentDataVo> data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
