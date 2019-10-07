package com.wuyan.wx.controller;

import com.wuyan.mall.bean.Address;
import com.wuyan.mall.bean.Order;
import com.wuyan.mall.bean.Region;
import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.wx.bean.*;
import com.wuyan.wx.config.UserTokenManager;
import com.wuyan.wx.service.personService.PersonService;
import com.wuyan.wx.utils.GetUserId;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Program: WUYANMALL
 * @Author: ZyEthan
 * @Description: 个人页面接口
 * @Date: 2019-10-05-19:36
 */
@RestController
@RequestMapping("wx")
public class PersonController {

    @Autowired
    PersonService personService;

    @RequestMapping("/user/index")
    public BaseRespVo userOrder(HttpServletRequest request) {
        String tokenKey = request.getHeader("X-cskaoyanmall-Admin-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
        if (userId == null) {
            return BaseRespVo.fail();
        }
        WxOrder wxOrder = new WxOrder();
        UserOrderStatus userOrderStatus = personService.getUserOrderStatus(userId);
        wxOrder.setOrder(userOrderStatus);
        BaseRespVo ok = BaseRespVo.ok(wxOrder);
        return ok;
    }

    @RequestMapping("/order/list")
    public BaseRespVo orderList(OrderPage orderPage,HttpServletRequest request) {
        String tokenKey = request.getHeader("X-cskaoyanmall-Admin-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
        if (userId == null) {
            return BaseRespVo.fail();
        }
        OrderPageList orderPageList = personService.getOrderList(orderPage,userId);
        BaseRespVo ok = BaseRespVo.ok(orderPageList);
        return ok;
    }
    @RequestMapping("/order/detail")
    public BaseRespVo orderDetail(Integer orderId,HttpServletRequest request) {
        Integer userId = GetUserId.getUserIdByRequest(request);
        OrderDetail orderDetail = personService.getOrderDrtail(orderId,userId);
        BaseRespVo ok = BaseRespVo.ok(orderDetail);
        return ok;
    }
    @RequestMapping("/address/list")
    public BaseRespVo addressList(HttpServletRequest request) {
        String tokenKey = request.getHeader("X-cskaoyanmall-Admin-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
        if (userId == null) {
            return BaseRespVo.fail();
        }
        List<AddressInfo> addressList = personService.getAddressList(userId);
        BaseRespVo ok = BaseRespVo.ok(addressList);
        return ok;
    }
    @RequestMapping("/address/detail")
    public BaseRespVo addressDetail(Integer id,HttpServletRequest request) {
        Integer userId = GetUserId.getUserIdByRequest(request);
        AddressDetail addressDetail = personService.getAddressDetail(id,userId);
        addressDetail.setId(id);
        BaseRespVo ok = BaseRespVo.ok(addressDetail);
        return ok;
    }
    @RequestMapping("/address/save")
    public BaseRespVo addressSave(@RequestBody AddressDetail addressDetail,HttpServletRequest request) {
        Integer userId = GetUserId.getUserIdByRequest(request);
        int id = personService.updateAddressDefault(addressDetail,userId);
        return BaseRespVo.ok(id);
    }
    @RequestMapping("region/list")
    public BaseRespVo regionList(Integer pid) {
        List<Region> regionList = personService.getRegionList(pid);
        BaseRespVo ok = BaseRespVo.ok(regionList);
        return ok;
    }
    @RequestMapping("/address/delete")
    public BaseRespVo deleteAddress(@RequestBody Address address) {
        int id = address.getId();
        int delete = personService.deleteAddress(id);
        BaseRespVo ok = BaseRespVo.ok(null);
        return ok;
    }
    @RequestMapping("order/prepay")
    public BaseRespVo orderPay(@RequestBody Order order) {
        Integer id = order.getId();
        BaseRespVo error = BaseRespVo.payError();
        return error;
    }
    // 取消订单
    @RequestMapping("order/cancel")
    public BaseRespVo orderCancel(@RequestBody Order order) {
        personService.orderCancel(order);
        BaseRespVo ok = BaseRespVo.ok(null);
        return ok;
    }
    @RequestMapping("order/confirm")
    public BaseRespVo orderConfirm(@RequestBody Order order) {
        personService.orderConfirm(order);
        BaseRespVo ok = BaseRespVo.ok(null);
        return ok;
    }

}
