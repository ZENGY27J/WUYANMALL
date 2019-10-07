package com.wuyan.wx.service.brandService;

import com.wuyan.mall.bean.Brand;
import com.wuyan.mall.vo.PageInfo;
import com.wuyan.wx.bean.Databean;

public interface WxBrandService {
    Databean getBrandList(PageInfo pageInfo);

    Brand getBrandDetail(int id);
}
