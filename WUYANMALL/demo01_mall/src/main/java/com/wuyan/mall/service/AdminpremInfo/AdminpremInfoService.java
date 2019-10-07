package com.wuyan.mall.service.AdminpremInfo;

import com.wuyan.mall.bean.permission.AdminPermInfo;

public interface AdminpremInfoService {

    AdminPermInfo getPermInfo(String principal);
}
