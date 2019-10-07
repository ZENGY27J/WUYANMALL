package com.wuyan.mall.vo;

import com.wuyan.mall.bean.Goods;
import com.wuyan.mall.bean.GoodsAttribute;
import com.wuyan.mall.bean.GoodsProduct;
import com.wuyan.mall.bean.GoodsSpecification;

import java.util.List;

public class SingleGoodsVo {
    Goods goods;
    int[] categoryIds;
    List<GoodsSpecification> specifications;
    List<GoodsProduct> products;
    List<GoodsAttribute> attributes;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int[] getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(int[] categoryIds) {
        this.categoryIds = categoryIds;
    }

    public List<GoodsSpecification> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<GoodsSpecification> specifications) {
        this.specifications = specifications;
    }

    public List<GoodsProduct> getProducts() {
        return products;
    }

    public void setProducts(List<GoodsProduct> products) {
        this.products = products;
    }

    public List<GoodsAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<GoodsAttribute> attributes) {
        this.attributes = attributes;
    }
}
