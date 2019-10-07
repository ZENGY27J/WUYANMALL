package com.wuyan.wx.service.catalogService;

import com.wuyan.wx.bean.CategoryBean;

public interface CatalogService {
    CategoryBean getCatalogIndex();

    CategoryBean getCatalogCurrent(int id);

}
