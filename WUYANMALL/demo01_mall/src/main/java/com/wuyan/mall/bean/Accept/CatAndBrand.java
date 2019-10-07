package com.wuyan.mall.bean.Accept;

import com.wuyan.mall.bean.Brand;
import com.wuyan.mall.bean.Category;

import java.util.List;

public class CatAndBrand {
    List<Cat> categoryList;

    List<Cat> brandList;

    @Override
    public String toString() {
        return "CatAndBrand{" +
                "categoryList=" + categoryList +
                ", brandList=" + brandList +
                '}';
    }

    public List<Cat> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Cat> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Cat> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<Cat> brandList) {
        this.brandList = brandList;
    }
}
