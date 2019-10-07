package com.wuyan.wx.bean;

/**
 * @Program: WUYANMALL
 * @Author: ZyEthan
 * @Description: 收货地址列表
 * @Date: 2019-10-06-18:02
 */
public class AddressInfo {
    // 详细地址
    String detailedAddress;
    // 地址id
    Integer id;
    // 是否是默认地址
    Boolean isDefault;
    // 收货人电话
    String mobile;
    // 收货人姓名
    String name;

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
