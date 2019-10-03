package com.wuyan.mall.vo;
/**
 * @Program: WUYANMALL
 * @Author: ZyEthan
 * @Description: 用户管理分页展示信息接收类
 * @Date: 2019-09-30-14:58
 */
public class UserPageInfo {
    // 当前页面
    int page;
    // 页面大小
    int limit;
    // 根据对应列排序
    String sort;
    // 排序方式
    String order;
    // 查找提交的用户名
    String username;
    // 查找提交的电话
    String mobile;
    // 收货人姓名
    String name;
    // 用户id
    String userId;
    // 商品id
    String valueId;
    // 商品id
    String goodsId;
    // 商品关键字
    String keyword;
    // 反馈id
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getValueId() {
        return valueId;
    }

    public void setValueId(String valueId) {
        this.valueId = valueId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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
}
