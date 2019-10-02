package com.wuyan.mall.bean.Accept;

import com.wuyan.mall.bean.Goods;
import com.wuyan.mall.bean.GoodsAttribute;
import com.wuyan.mall.bean.GoodsProduct;
import com.wuyan.mall.bean.GoodsSpecification;

import java.util.Arrays;

public class CreateGoods {
    Goods goods;
    GoodsSpecification[] specifications;
    GoodsProduct[] products;
    GoodsAttribute[] attributes;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public GoodsSpecification[] getSpecifications() {
        return specifications;
    }

    public void setSpecifications(GoodsSpecification[] specifications) {
        this.specifications = specifications;
    }

    public GoodsProduct[] getProducts() {
        return products;
    }

    public void setProducts(GoodsProduct[] products) {
        this.products = products;
    }

    public GoodsAttribute[] getAttributes() {
        return attributes;
    }

    public void setAttributes(GoodsAttribute[] attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "CreateGoods{" +
                "goods=" + goods +
                ", specifications=" + Arrays.toString(specifications) +
                ", products=" + Arrays.toString(products) +
                ", attributes=" + Arrays.toString(attributes) +
                '}';
    }
}
