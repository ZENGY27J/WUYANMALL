package com.wuyan.mall.service.user;

import com.wuyan.mall.bean.Collect;
<<<<<<< HEAD
import com.wuyan.mall.bean.Footprint;
import com.wuyan.mall.bean.SearchHistory;
import com.wuyan.mall.bean.UserMagerBean.AddressPage;
import com.wuyan.mall.bean.UserMagerBean.BaseData;
import com.wuyan.mall.bean.UserMagerBean.UserPage;
import com.wuyan.mall.vo.PageInfo;
=======
import com.wuyan.mall.bean.Feedback;
import com.wuyan.mall.bean.Footprint;
import com.wuyan.mall.bean.SearchHistory;
import com.wuyan.mall.bean.UserMagerBean.AddressPage;
import com.wuyan.mall.bean.BaseData;
import com.wuyan.mall.bean.UserMagerBean.UserPage;
import com.wuyan.mall.vo.UserPageInfo;
>>>>>>> zydevone

/**
 * 用户管理接口
 */
public interface UserService {
<<<<<<< HEAD
    UserPage getUserPage(PageInfo pageInfo);

    AddressPage getAddressPage(PageInfo pageInfo);

    BaseData<Collect> getUserCollect(PageInfo pageInfo);

    BaseData<Footprint> getFootprint(PageInfo pageInfo);

    BaseData<SearchHistory> getHistory(PageInfo pageInfo);
=======
    UserPage getUserPage(UserPageInfo pageInfo);

    AddressPage getAddressPage(UserPageInfo pageInfo);

    BaseData<Collect> getUserCollect(UserPageInfo pageInfo);

    BaseData<Footprint> getFootprint(UserPageInfo pageInfo);

    BaseData<SearchHistory> getHistory(UserPageInfo pageInfo);

    BaseData<Feedback> getFeedback(UserPageInfo pageInfo);
>>>>>>> zydevone

}
