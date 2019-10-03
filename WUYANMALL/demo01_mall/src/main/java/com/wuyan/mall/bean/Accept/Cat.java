package com.wuyan.mall.bean.Accept;

import java.util.List;

public class Cat {
    private int value;
    private String label;
    List<Cat> children;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Cat> getChildren() {
        return children;
    }

    public void setChildren(List<Cat> children) {
        this.children = children;
    }
}
