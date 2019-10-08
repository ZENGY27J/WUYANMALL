package com.wuyan.wx.utils;

import com.github.pagehelper.PageHelper;
import com.wuyan.mall.bean.*;
import com.wuyan.mall.vo.PageInfo;

public class QueryUtils {
    //分页
    public static BrandExample getBrand(int size){
        PageHelper.startPage(1, size);
        return new BrandExample();
    }


    public static CategoryExample getCategory(int size,int pid){
        CategoryExample categoryExample = new CategoryExample();
        PageHelper.startPage(1,size);
        categoryExample.createCriteria().andPidEqualTo(pid);
        return categoryExample;
    }
    public static CategoryExample getCategory(String level){
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andLevelEqualTo(level);
        return categoryExample;
    }
    public static CategoryExample getCategoryById(int id) {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andIdEqualTo(id);
        return categoryExample;
    }
    public static CouponExample getCoupon(int couponId){
        CouponExample couponExample = new CouponExample();
        couponExample.createCriteria().andIdEqualTo(couponId);
        return couponExample;
    }

    public static CouponExample getCoupon(int page, int size) {
        PageHelper.startPage(page, size);
        return new CouponExample();
    }

    public static GoodsExample getGoodsByCategoryId(int categoryId,int size){
        PageHelper.startPage(1,size);
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andCategoryIdEqualTo(categoryId);
        return goodsExample;
    }
    public static GoodsExample getGoodsByCategoryId(PageInfo pageInfo){
        PageHelper.startPage(pageInfo.getPage(),pageInfo.getSize());
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andCategoryIdEqualTo(pageInfo.getCategoryId());
        return goodsExample;
    }

    public static GoodsExample getHotGoods(int size){
        GoodsExample goodsExample = new GoodsExample();
        PageHelper.startPage(1,size);
        goodsExample.createCriteria().andIsHotEqualTo(true);
        return goodsExample;
    }
    public static GoodsExample getHotGoods(PageInfo pageInfo){
        PageHelper.startPage(pageInfo.getPage(),pageInfo.getSize(), pageInfo.getSort() + " " + pageInfo.getOrder());
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andIsHotEqualTo(true);
        return goodsExample;
    }
    public static GoodsExample getNewGoods(PageInfo pageInfo){
        PageHelper.startPage(pageInfo.getPage(),pageInfo.getSize(), pageInfo.getSort() + " " + pageInfo.getOrder());
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andIsNewEqualTo(true);
        return goodsExample;
    }
    public static GoodsExample getNewGoods(int size){
        GoodsExample goodsExample = new GoodsExample();
        PageHelper.startPage(1,size);
        goodsExample.createCriteria().andIsNewEqualTo(true);
        return goodsExample;
    }
    public static GoodsExample getGoodsById(int id){
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andIdEqualTo(id);
        return goodsExample;
    }
    //获取团购信息
    public static GrouponRulesExample getGroupon(int size){
        PageHelper.startPage(1,5);
        return new GrouponRulesExample();
    }
    public static TopicExample getTopic(int size){
        PageHelper.startPage(1,size);
        return new TopicExample();
    }

    public static KeywordExample getDefaultKeyword() {
        KeywordExample keywordExample = new KeywordExample();
        keywordExample.createCriteria().andIsDefaultEqualTo(true);
        return keywordExample;
    }

    public static KeywordExample getHotKeyword() {
        KeywordExample keywordExample = new KeywordExample();
        keywordExample.createCriteria().andIsHotEqualTo(true);
        return keywordExample;
    }

    public static SearchHistoryExample getSearchHistory(int id) {
        SearchHistoryExample searchHistoryExample = new SearchHistoryExample();
        searchHistoryExample.createCriteria().andUserIdEqualTo(id);
        return searchHistoryExample;
    }

    public static KeywordExample getLikeKeyword(String keyword) {
        KeywordExample keywordExample = new KeywordExample();
        keywordExample.createCriteria().andKeywordLike("%" + keyword + "%");
        return keywordExample;
    }

    public static GoodsExample getGoodsByKeyword(String keyword) {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andKeywordsLike("%" + keyword + "%");
        return goodsExample;
    }
    public static GoodsExample getGoodsByKeyword(PageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPage(),pageInfo.getSize(), pageInfo.getSort() + " " + pageInfo.getOrder());
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andKeywordsLike("%" + pageInfo.getKeyword() + "%");
        return goodsExample;
    }

    public static TopicExample getTopicById(int id) {
        TopicExample topicExample = new TopicExample();
        topicExample.createCriteria().andIdEqualTo(id);
        return topicExample;
    }

    public static CouponExample getCouponByCode(String code) {
        CouponExample couponExample = new CouponExample();
        couponExample.createCriteria().andCodeEqualTo(code);
        return couponExample;
    }
}
