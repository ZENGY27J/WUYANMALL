package com.wuyan.wx.vo;

import com.wuyan.mall.bean.*;


import java.util.List;

public class GoodsDetail {
    //分享商品时的图片
    String shareImage;
    //是否已经收藏
    Boolean userHasCollect;
    List<Issue>  issue;
    Brand brand;
    List<GrouponRules> groupon;
    CommentVo comment;
    Goods info;
    List<GoodsProduct> productList;
    List<SpecificationVo> specificationList;
    List<GoodsAttribute> attribute;

    public List<GoodsAttribute> getAttribute() {
        return attribute;
    }

    public void setAttribute(List<GoodsAttribute> attribute) {
        this.attribute = attribute;
    }

    public String getShareImage() {
        return shareImage;
    }

    public void setShareImage(String shareImage) {
        this.shareImage = shareImage;
    }

    public Boolean getUserHasCollect() {
        return userHasCollect;
    }

    public void setUserHasCollect(Boolean userHasCollect) {
        this.userHasCollect = userHasCollect;
    }

    public List<Issue> getIssue() {
        return issue;
    }

    public void setIssue(List<Issue> issue) {
        this.issue = issue;
    }



    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<GrouponRules> getGroupon() {
        return groupon;
    }

    public void setGroupon(List<GrouponRules> groupon) {
        this.groupon = groupon;
    }

    public CommentVo getComment() {
        return comment;
    }

    public void setComment(CommentVo comment) {
        this.comment = comment;
    }

    public Goods getInfo() {
        return info;
    }

    public void setInfo(Goods info) {
        this.info = info;
    }

    public List<GoodsProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<GoodsProduct> productList) {
        this.productList = productList;
    }

    public List<SpecificationVo> getSpecificationList() {
        return specificationList;
    }

    public void setSpecificationList(List<SpecificationVo> specificationList) {
        this.specificationList = specificationList;
    }
}
