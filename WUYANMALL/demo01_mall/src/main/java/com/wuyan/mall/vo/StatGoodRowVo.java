package com.wuyan.mall.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 商品统计的列对象
 * @Param:
 * @return:
 * @Author: fangbo
 * @Date: 2019/10/2
 */

public class StatGoodRowVo {

    BigDecimal amount;
    int orders;

    @JsonFormat(pattern = "yyyy-MM-dd")
    Date day;
    int products;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public int getProducts() {
        return products;
    }

    public void setProducts(int products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "StatGoodRowVo{" +
                "amount=" + amount +
                ", orders=" + orders +
                ", day='" + day + '\'' +
                ", products=" + products +
                '}';
    }
}
