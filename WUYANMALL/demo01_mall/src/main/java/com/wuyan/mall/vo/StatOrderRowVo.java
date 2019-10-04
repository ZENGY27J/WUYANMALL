package com.wuyan.mall.vo;

import java.math.BigDecimal;

/**
 * @Description: 订单统计返回的行数据
 * @Param:
 * @return:
 * @Author: fangbo
 * @Date: 2019/10/2
 */
public class StatOrderRowVo {
    BigDecimal amount;
    int orders;
    int customers;
    String day;
    BigDecimal pcr;

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

    public int getCustomers() {
        return customers;
    }

    public void setCustomers(int customers) {
        this.customers = customers;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public BigDecimal getPcr() {
        return pcr;
    }

    public void setPcr(BigDecimal pcr) {
        this.pcr = pcr;
    }

    @Override
    public String toString() {
        return "StatOrderRowVo{" +
                "amount=" + amount +
                ", orders=" + orders +
                ", customers=" + customers +
                ", day='" + day + '\'' +
                ", pcr=" + pcr +
                '}';
    }
}
