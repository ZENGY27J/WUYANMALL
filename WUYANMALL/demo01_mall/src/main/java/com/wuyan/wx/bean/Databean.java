package com.wuyan.wx.bean;

import com.wuyan.mall.bean.Brand;
import com.wuyan.mall.bean.Category;
import com.wuyan.mall.bean.Goods;

import java.util.List;

public class Databean<T> {
    long count;
    long totalPages;
    List<T> data;
    List<Category> filterCategoryList;
    List<Goods> goodsList;
    List<Brand> brandList;

    public List<Brand> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<Brand> brandList) {
        this.brandList = brandList;
    }

    public List<Category> getFilterCategoryList() {
        return filterCategoryList;
    }

    public void setFilterCategoryList(List<Category> filterCategoryList) {
        this.filterCategoryList = filterCategoryList;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
