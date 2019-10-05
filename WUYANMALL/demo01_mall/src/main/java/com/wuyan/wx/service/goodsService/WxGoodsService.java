package com.wuyan.wx.service.goodsService;

import com.wuyan.mall.vo.PageInfo;
import com.wuyan.wx.bean.CategoryBean;
import com.wuyan.wx.bean.Databean;

public interface WxGoodsService {
    Databean getGoodsList(PageInfo pageInfo);

    CategoryBean getGoodsCategory(int id);
}
