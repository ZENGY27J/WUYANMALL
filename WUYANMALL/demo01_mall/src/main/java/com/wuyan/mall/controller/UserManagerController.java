package com.wuyan.mall.controller;

import com.wuyan.mall.bean.Collect;
<<<<<<< HEAD
import com.wuyan.mall.bean.Footprint;
import com.wuyan.mall.bean.SearchHistory;
import com.wuyan.mall.bean.UserMagerBean.AddressPage;
import com.wuyan.mall.bean.UserMagerBean.BaseData;
import com.wuyan.mall.bean.UserMagerBean.UserPage;
import com.wuyan.mall.service.user.UserService;
import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.mall.vo.PageInfo;
=======
import com.wuyan.mall.bean.Feedback;
import com.wuyan.mall.bean.Footprint;
import com.wuyan.mall.bean.SearchHistory;
import com.wuyan.mall.bean.UserMagerBean.AddressPage;
import com.wuyan.mall.bean.BaseData;
import com.wuyan.mall.bean.UserMagerBean.UserPage;
import com.wuyan.mall.service.user.UserService;
import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.mall.vo.UserPageInfo;
>>>>>>> zydevone
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
<<<<<<< HEAD
    public BaseRespVo userList(PageInfo pageInfo) {
=======
    public BaseRespVo userList(UserPageInfo pageInfo) {
>>>>>>> zydevone
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
<<<<<<< HEAD
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
=======
    public BaseRespVo addressList(UserPageInfo pageInfo) {
        AddressPage addressPage = userService.getAddressPage(pageInfo);
        if (addressPage == null) {
            return BaseRespVo.error(addressPage);
        }
        BaseRespVo ok = BaseRespVo.ok(addressPage);
        return ok;
    }

    /**
     * 会员收藏
     * @param pageInfo
     * @return
     */
    @RequestMapping("/collect/list")
    public BaseRespVo userCollect(UserPageInfo pageInfo) {
        BaseData<Collect> userCollect = userService.getUserCollect(pageInfo);
        if (userCollect == null) {
            return BaseRespVo.error(userCollect);
        }
        BaseRespVo ok = BaseRespVo.ok(userCollect);
        return ok;
    }

    /**
     * 会员足迹
     * @param pageInfo
     * @return
     */
    @RequestMapping("/footprint/list")
    public BaseRespVo userFootprint(UserPageInfo pageInfo) {
        BaseData<Footprint> footprint = userService.getFootprint(pageInfo);
        if (footprint == null) {
            return BaseRespVo.error(footprint);
        }
        BaseRespVo ok = BaseRespVo.ok(footprint);
        return ok;
    }

    /**
     * 搜索历史
     * @param pageInfo
     * @return
     */
    @RequestMapping("/history/list")
    public BaseRespVo searchHistory(UserPageInfo pageInfo) {
        BaseData<SearchHistory> serviceHistory = userService.getHistory(pageInfo);
        if (serviceHistory == null) {
            return BaseRespVo.error(serviceHistory);
        }
        BaseRespVo ok = BaseRespVo.ok(serviceHistory);
        return ok;
    }

    /**
     * 意见反馈
     * @param pageInfo
     * @return
     */
    @RequestMapping("/feedback/list")
    public BaseRespVo feedback(UserPageInfo pageInfo) {
        BaseData<Feedback> feedback = userService.getFeedback(pageInfo);
        if (feedback == null) {
            return BaseRespVo.error(feedback);
        }
        BaseRespVo ok = BaseRespVo.ok(feedback);
        return ok;
    }
>>>>>>> zydevone
}
