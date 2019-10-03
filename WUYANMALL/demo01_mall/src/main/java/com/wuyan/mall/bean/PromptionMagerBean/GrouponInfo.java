package com.wuyan.mall.bean.PromptionMagerBean;

import com.wuyan.mall.bean.Goods;
import com.wuyan.mall.bean.Groupon;
import com.wuyan.mall.bean.GrouponRules;

/**
 * @Program: WUYANMALL
 * @Author: ZyEthan
 * @Description:
 * @Date: 2019-10-02-23:17
 */
public class GrouponInfo {
    Groupon groupon;
    Goods goods;
    GrouponRules grouponRules;
    String[] subGroupons;

    public Groupon getGroupon() {
        return groupon;
    }

    public void setGroupon(Groupon groupon) {
        this.groupon = groupon;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public GrouponRules getGrouponRules() {
        return grouponRules;
    }

    public void setGrouponRules(GrouponRules grouponRules) {
        this.grouponRules = grouponRules;
    }

    public String[] getSubGroupons() {
        return subGroupons;
    }

    public void setSubGroupons(String[] subGroupons) {
        this.subGroupons = subGroupons;
    }
}
