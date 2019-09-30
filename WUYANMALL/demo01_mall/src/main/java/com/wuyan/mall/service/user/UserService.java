package com.wuyan.mall.service.user;

import com.wuyan.mall.bean.Collect;
import com.wuyan.mall.bean.Footprint;
import com.wuyan.mall.bean.SearchHistory;
import com.wuyan.mall.bean.UserMagerBean.AddressPage;
import com.wuyan.mall.bean.UserMagerBean.BaseData;
import com.wuyan.mall.bean.UserMagerBean.UserPage;
import com.wuyan.mall.vo.PageInfo;

/**
 * 用户管理接口
 */
public interface UserService {
    UserPage getUserPage(PageInfo pageInfo);

    AddressPage getAddressPage(PageInfo pageInfo);

    BaseData<Collect> getUserCollect(PageInfo pageInfo);

    BaseData<Footprint> getFootprint(PageInfo pageInfo);

    BaseData<SearchHistory> getHistory(PageInfo pageInfo);

}
