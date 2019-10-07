package com.wuyan.wx.service.catalogService;

import com.wuyan.wx.bean.CategoryBean;

public interface WxCatalogService {
    CategoryBean getCatalogIndex();

    CategoryBean getCatalogCurrent(int id);

}
