package com.wuyan.mall.bean.UserMagerBean;

import com.wuyan.mall.bean.Address;
import com.wuyan.mall.bean.User;

import java.util.List;

/**
 * @Program: WUYANMALL
 * @Author: ZyEthan
 * @Description: 收货地址管理
 * @Date: 2019-09-30-17:35
 */
public class AddressPage {
    long total;
    List<Address> items;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<Address> getItems() {
        return items;
    }

    public void setItems(List<Address> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "AddressPage{" +
                "total=" + total +
                ", items=" + items +
                '}';
    }
}
