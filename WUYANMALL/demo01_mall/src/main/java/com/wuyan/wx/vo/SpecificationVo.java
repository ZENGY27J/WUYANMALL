package com.wuyan.wx.vo;

import com.wuyan.mall.bean.GoodsSpecification;

import java.util.List;

public class SpecificationVo {
    String name;
    List<GoodsSpecification> valueList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GoodsSpecification> getValueList() {
        return valueList;
    }

    public void setValueList(List<GoodsSpecification> valueList) {
        this.valueList = valueList;
    }
}
