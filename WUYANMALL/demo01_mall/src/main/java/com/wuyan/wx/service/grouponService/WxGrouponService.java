package com.wuyan.wx.service.grouponService;

import com.wuyan.mall.vo.PageInfo;
import com.wuyan.wx.bean.Databean;

public interface WxGrouponService {
    Databean getGroupon(PageInfo pageInfo);
}
