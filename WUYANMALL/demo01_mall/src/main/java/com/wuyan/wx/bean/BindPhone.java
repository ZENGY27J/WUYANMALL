package com.wuyan.wx.bean;

/**
 * @Program: WUYANMALL
 * @Author: ZyEthan
 * @Description: 绑定手机
 * @Date: 2019-10-07-15:00
 */
public class BindPhone {
    String encryptedData;
    String iv;

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }
}
