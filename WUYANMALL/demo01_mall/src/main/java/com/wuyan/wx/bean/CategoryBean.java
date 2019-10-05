package com.wuyan.wx.bean;

import com.wuyan.mall.bean.Category;

import java.util.List;

public class CategoryBean {
    List<Category> categoryList;
    Category currentCategory;
    List<Category> currentSubCategory;

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public Category getCurrentCategory() {
        return currentCategory;
    }

    public void setCurrentCategory(Category currentCategory) {
        this.currentCategory = currentCategory;
    }

    public List<Category> getCurrentSubCategory() {
        return currentSubCategory;
    }

    public void setCurrentSubCategory(List<Category> currentSubCategory) {
        this.currentSubCategory = currentSubCategory;
    }

    public CategoryBean() {
    }

    public CategoryBean(List<Category> categoryList, Category currentCategory, List<Category> currentSubCategory) {
        this.categoryList = categoryList;
        this.currentCategory = currentCategory;
        this.currentSubCategory = currentSubCategory;
    }
}
