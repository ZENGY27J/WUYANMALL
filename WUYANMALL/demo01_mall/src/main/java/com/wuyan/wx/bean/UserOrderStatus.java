package com.wuyan.wx.bean;

/**
 * @Program: WUYANMALL
 * @Author: ZyEthan
 * @Description: 个人界面用户订单状态
 * @Date: 2019-10-05-17:21
 */
public class UserOrderStatus {
    int uncomment;
    int unpaid;
    int unrecv;
    int unship;

    public int getUncomment() {
        return uncomment;
    }

    public void setUncomment(int uncomment) {
        this.uncomment = uncomment;
    }

    public int getUnpaid() {
        return unpaid;
    }

    public void setUnpaid(int unpaid) {
        this.unpaid = unpaid;
    }

    public int getUnrecv() {
        return unrecv;
    }

    public void setUnrecv(int unrecv) {
        this.unrecv = unrecv;
    }

    public int getUnship() {
        return unship;
    }

    public void setUnship(int unship) {
        this.unship = unship;
    }
}
