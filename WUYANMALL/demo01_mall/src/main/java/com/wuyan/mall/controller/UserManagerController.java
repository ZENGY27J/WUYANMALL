package com.wuyan.mall.controller;

import com.wuyan.mall.bean.Collect;
import com.wuyan.mall.bean.Footprint;
import com.wuyan.mall.bean.SearchHistory;
import com.wuyan.mall.bean.UserMagerBean.AddressPage;
import com.wuyan.mall.bean.UserMagerBean.BaseData;
import com.wuyan.mall.bean.UserMagerBean.UserPage;
import com.wuyan.mall.service.user.UserService;
import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.mall.vo.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Program: WUYANMALL
 * @Author: ZyEthan
 * @Description: 用户管理
 * @Date: 2019-09-30-14:38
 */
@RestController
@RequestMapping("admin")
public class UserManagerController {
    @Autowired
    UserService userService;

    /**
     * user列表
     * @param pageInfo
     * @return
     */
    @RequestMapping("/user/list")
    public BaseRespVo userList(PageInfo pageInfo) {
        UserPage userPage = userService.getUserPage(pageInfo);
        BaseRespVo ok = BaseRespVo.ok(userPage);
        return ok;
    }

    /**
     * 收货地址列表
     * @param pageInfo
     * @return
     */
    @RequestMapping("/address/list")
    public BaseRespVo addressList(PageInfo pageInfo) {
        AddressPage addressPage = userService.getAddressPage(pageInfo);
        BaseRespVo ok = BaseRespVo.ok(addressPage);
        return ok;
    }
    @RequestMapping("/collect/list")
    public BaseRespVo userCollect(PageInfo pageInfo) {
        BaseData<Collect> userCollect = userService.getUserCollect(pageInfo);
        BaseRespVo ok = BaseRespVo.ok(userCollect);
        return ok;
    }
    @RequestMapping("/footprint/list")
    public BaseRespVo userFootprint(PageInfo pageInfo) {
        BaseData<Footprint> footprint = userService.getFootprint(pageInfo);
        BaseRespVo ok = BaseRespVo.ok(footprint);
        return ok;
    }
    @RequestMapping("/history/list")
    public BaseRespVo searchHistory(PageInfo pageInfo) {
        BaseData<SearchHistory> serviceHistory = userService.getHistory(pageInfo);
        BaseRespVo ok = BaseRespVo.ok(serviceHistory);
        return ok;
    }
}
