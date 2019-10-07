package com.wuyan.mall.vo;

/**
 * @Program: WUYANMALL
 * @Author: ZyEthan
 * @Description: 推广管理分页展示信息接收类
 * @Date: 2019-10-01-15:46
 */
public class PromotionPageInfo {
    // 当前页面
    int page;
    // 页面大小
    int limit;
    // 根据对应列排序
    String sort;
    // 排序方式
    String order;
    // 广告标题/优惠券标题
    String name;
    // 广告内容
    String content;
    // 优惠券类型
    String type;
    // 优惠券状态
    String status;
    // 优惠券id
    String couponId;
    // 用户id
    String userId;
    // 商品id
    Integer GoodsId;
    String title;
    String subtitle;

    public Integer getGoodsId() {
        return GoodsId;
    }

    public void setGoodsId(Integer goodsId) {
        GoodsId = goodsId;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
