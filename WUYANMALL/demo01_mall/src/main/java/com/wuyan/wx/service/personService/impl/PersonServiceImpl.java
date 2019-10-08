package com.wuyan.wx.service.personService.impl;

import com.wuyan.mall.bean.*;
import com.wuyan.mall.mapper.*;
import com.wuyan.wx.bean.*;
import com.wuyan.wx.config.UserTokenManager;
import com.wuyan.wx.service.personService.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Program: WUYANMALL
 * @Author: ZyEthan
 * @Description: 个人中心业务层具体实现类
 * @Date: 2019-10-05-15:59
 */
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    GrouponMapper grouponMapper;
    @Autowired
    OrderGoodsMapper orderGoodsMapper;
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    RegionMapper regionMapper;
    @Autowired
    FeedbackMapper feedbackMapper;

    @Override
    public UserLoginInfo queryUser(UserToken userToken) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(userToken.getUsername()).andPasswordEqualTo(userToken.getPassword());
        List<User> users = userMapper.selectByExample(userExample);
        User user = users.get(0);
        int userId = user.getId();
        UserToken myuserToken = UserTokenManager.generateToken(userId);
        HashMap<String, String> userInfo = new HashMap<>();
        userInfo.put("nickName", user.getNickname());
        userInfo.put("avatarUrl", user.getAvatar());
        UserLoginInfo userLoginInfo = new UserLoginInfo();
        userLoginInfo.setTokenExpire(user.getUpdateTime().toString());
        userLoginInfo.setUserInfo(userInfo);
        userLoginInfo.setToken(myuserToken.getToken());
        return userLoginInfo;
    }

    @Override
    public UserOrderStatus getUserOrderStatus(Integer userId) {
        UserOrderStatus userOrderStatus = new UserOrderStatus();
        OrderExample orderExample1 = new OrderExample();
        OrderExample orderExample2 = new OrderExample();
        OrderExample orderExample3 = new OrderExample();
        OrderExample orderExample4 = new OrderExample();
        OrderExample.Criteria criteria1 = orderExample1.createCriteria();
        OrderExample.Criteria criteria2 = orderExample2.createCriteria();
        OrderExample.Criteria criteria3 = orderExample3.createCriteria();
        OrderExample.Criteria criteria4 = orderExample4.createCriteria();
        criteria1.andOrderStatusEqualTo((short) 101).andUserIdEqualTo(userId);
        criteria2.andOrderStatusEqualTo((short) 201).andUserIdEqualTo(userId);
        criteria3.andOrderStatusEqualTo((short) 301).andUserIdEqualTo(userId);
        criteria4.andOrderStatusEqualTo((short) 401).andUserIdEqualTo(userId);
        long unpaid = orderMapper.countByExample(orderExample1);
        long unship = orderMapper.countByExample(orderExample2);
        long unrecv = orderMapper.countByExample(orderExample3);
        long uncomment = orderMapper.countByExample(orderExample4);
        userOrderStatus.setUnpaid((short) unpaid);
        userOrderStatus.setUnship((short) unship);
        userOrderStatus.setUnrecv((short) unrecv);
        userOrderStatus.setUncomment((short) uncomment);
        return userOrderStatus;
    }

    @Override
    public OrderPageList getOrderList(OrderPage orderPage, Integer userId) {
        int showType = orderPage.getShowType();
        OrderPageList orderPageList = new OrderPageList();
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        if (showType == 0) {
            criteria.andUserIdEqualTo(userId);
        } else {
            criteria.andUserIdEqualTo(userId).andOrderStatusEqualTo((short) (showType*100+1));
        }

        // 总订单数
        long count = orderMapper.countByExample(orderExample);
        List<Order> orders = orderMapper.selectByExample(orderExample);
        long pages = count / orderPage.getSize();
        pages = count % orderPage.getPage() == 0 ? pages : pages + 1;
        List<UserOrderInfo> userOrderInfos = new LinkedList<>();
        for (Order order : orders) {
            UserOrderInfo userOrderInfo = new UserOrderInfo();
            userOrderInfo.setActualPrice(order.getActualPrice());
            userOrderInfo.setOrderSn(order.getOrderSn());
            userOrderInfo.setId(order.getId());
            Short orderStatus = order.getOrderStatus();
            String orderStatusText = null;
            if (orderStatus == 101) {
                orderStatusText = "未付款";
            } else if ( orderStatus == 201) {
                orderStatusText = "已付款";
            } else if ( orderStatus == 301) {
                orderStatusText = "已发货";
            } else if ( orderStatus == 401) {
                orderStatusText = "已收货";
            }
            userOrderInfo.setOrderStatusText(orderStatusText);
            GrouponExample example = new GrouponExample();
            example.createCriteria().andOrderIdEqualTo(order.getId());
            long l = grouponMapper.countByExample(example);
            userOrderInfo.setGroupin(true);
            if (l == 0){
                userOrderInfo.setGroupin(false);
            }
            // goodsList
            // 通过orderid查ordergoods表
            List<UserGoodsInfo> userGoodsInfos = new LinkedList<>();
            UserGoodsInfo userGoodsInfo = new UserGoodsInfo();
            Integer orderId = order.getId();
            OrderGoodsExample orderGoodsExample = new OrderGoodsExample();
            OrderGoodsExample.Criteria orderGoodsExampleCriteria = orderGoodsExample.createCriteria();
            orderGoodsExampleCriteria.andOrderIdEqualTo(orderId);
            List<OrderGoods> orderGoods = orderGoodsMapper.selectByExample(orderGoodsExample);
            for (OrderGoods orderGood : orderGoods) {
                userGoodsInfo.setGoodsName(orderGood.getGoodsName());
                userGoodsInfo.setId(orderGood.getGoodsId());
                userGoodsInfo.setNumber(orderGood.getNumber());
                userGoodsInfo.setPicUrl(orderGood.getPicUrl());
                userGoodsInfos.add(userGoodsInfo);
            }
            userOrderInfo.setGoodsList(userGoodsInfos);
            // handleOption
            HandleOption handleOption = new HandleOption();
            handleOption.setCancel(false);
            handleOption.setComment(false);
            handleOption.setConfirm(false);
            handleOption.setDelete(order.getDeleted());
            handleOption.setPay(false);
            handleOption.setRebuy(false);
            handleOption.setRefund(false);
            if (order.getOrderStatus() == 102 || order.getOrderStatus() == 103) {
                handleOption.setCancel(true);
            }
            if (order.getComments() == 0) {
                handleOption.setComment(true);
            }
            if (order.getConfirmTime() != null) {
                handleOption.setConfirm(true);
            }
            if (order.getOrderStatus() == 201 || order.getOrderStatus() == 202) {
                handleOption.setPay(true);
            }
            userOrderInfo.setHandleOption(handleOption);
            userOrderInfos.add(userOrderInfo);
        }

        orderPageList.setCount((int) count);
        orderPageList.setTotalPages((int) pages);
        orderPageList.setData(userOrderInfos);
        return orderPageList;
    }

    @Override
    public OrderDetail getOrderDrtail(Integer orderId, Integer userId) {
        OrderDetail orderDetail = new OrderDetail();
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<Order> orders = orderMapper.selectByExample(orderExample);
        Order myorder = null;
        for (Order order : orders) {
            if(order.getId() == orderId) {
                myorder = order;
            }
        }
        HandleOption handleOption = new HandleOption();
        handleOption.setCancel(false);
        handleOption.setComment(false);
        handleOption.setConfirm(false);
        handleOption.setDelete(myorder.getDeleted());
        handleOption.setPay(false);
        handleOption.setRebuy(false);
        handleOption.setRefund(false);
        if (myorder.getOrderStatus() == 102 || myorder.getOrderStatus() == 103) {
            handleOption.setCancel(true);
        }
        if (myorder.getComments() == 0) {
            handleOption.setComment(true);
        }
        if (myorder.getConfirmTime() != null) {
            handleOption.setConfirm(true);
        }
        if (myorder.getOrderStatus() == 201 || myorder.getOrderStatus() == 202) {
            handleOption.setPay(true);
        }
        myorder.setHandleOption(handleOption);
        myorder.setOrderStatusText("未付款");
        if (handleOption.getCancel() == true) {
            myorder.setOrderStatusText("已取消");
        }
        if (handleOption.getPay() == true) {
            myorder.setOrderStatusText("已付款");
        }
        if (handleOption.getConfirm() == true) {
            myorder.setOrderStatusText("待收货");
        }
        BigDecimal subtract = myorder.getGoodsPrice().add(myorder.getFreightPrice()).subtract(myorder.getCouponPrice());
        myorder.setActualPrice(subtract);

        OrderGoodsExample orderGoodsExample = new OrderGoodsExample();
        OrderGoodsExample.Criteria criteria1 = orderGoodsExample.createCriteria();
        criteria1.andOrderIdEqualTo(orderId);
        List<OrderGoods> orderGoods = orderGoodsMapper.selectByExample(orderGoodsExample);
        orderDetail.setOrderGoods(orderGoods);
        orderDetail.setOrderInfo(myorder);
        return orderDetail;
    }

    @Override
    public List<AddressInfo> getAddressList(Integer userId) {
        List<AddressInfo> addressInfos = new ArrayList<>();
        AddressExample addressExample = new AddressExample();
        AddressExample.Criteria criteria = addressExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<Address> addresses = addressMapper.selectByExample(addressExample);
        for (Address address : addresses) {
            AddressInfo addressInfo = new AddressInfo();
            addressInfo.setId(address.getId());
            addressInfo.setName(address.getName());
            addressInfo.setIsDefault(address.getDefault());
            addressInfo.setMobile(address.getMobile());
            Integer provinceId = address.getProvinceId();
            Integer cityId = address.getCityId();
            Integer areaId = address.getAreaId();
            regionMapper.queryNameByCode(provinceId);
            StringBuilder addressDetail = new StringBuilder(regionMapper.queryNameByCode(provinceId));
            addressDetail.append(regionMapper.queryNameByCode(cityId)).append(regionMapper.queryNameByCode(areaId)).append("  ").append(address.getAddress());
            addressInfo.setDetailedAddress(addressDetail.toString());
            addressInfos.add(addressInfo);
        }
        return addressInfos;
    }

    @Override
    public AddressDetail getAddressDetail(Integer id,Integer userId) {
        AddressDetail addressDetail = new AddressDetail();
        AddressExample addressExample = new AddressExample();
        AddressExample.Criteria criteria = addressExample.createCriteria();
        criteria.andIdEqualTo(id);
        List<Address> addresses = addressMapper.selectByExample(addressExample);
        if (addresses == null) {
            return null;
        }
        Address address = addresses.get(0);
        addressDetail.setId(address.getId());
        addressDetail.setProvinceId(address.getProvinceId());
        addressDetail.setCityId(address.getCityId());
        addressDetail.setAreaId(address.getAreaId());
        addressDetail.setAddress(address.getAddress());
        addressDetail.setName(address.getName());
        addressDetail.setMobile(address.getMobile());
        addressDetail.setIsDefault(address.getDefault());
        addressDetail.setProvinceName(regionMapper.queryNameByCode(address.getProvinceId()));
        addressDetail.setCityName(regionMapper.queryNameByCode(address.getCityId()));
        addressDetail.setAreaName(regionMapper.queryNameByCode(address.getAreaId()));
        return addressDetail;
    }

    @Override
    public int updateAddressDefault(AddressDetail addressDetail, Integer userId) {
        // 首先取消当前的默认地址
        AddressExample addressExample = new AddressExample();
        AddressExample.Criteria criteria = addressExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<Address> addresses = addressMapper.selectByExample(addressExample);
        Boolean isDefault = addressDetail.getIsDefault();
        // 插入新的地址
        if (addressDetail.getId() == 0) {
            addressDetail.setUserId(userId);
            addressDetail.setDeleted(false);
            addressDetail.setAddTime(new Date());
            addressDetail.setUpdateTime(new Date());
            int pid = addressMapper.queryCodeById(addressDetail.getProvinceId());
            int cid = addressMapper.queryCodeById(addressDetail.getCityId());
            int aid = addressMapper.queryCodeById(addressDetail.getAreaId());
            addressDetail.setProvinceId(pid);
            addressDetail.setCityId(cid);
            addressDetail.setAreaId(aid);
            int insert = addressMapper.insert(addressDetail);
        }
        // 如果修改默认地址
        if (isDefault == true) {
            for (Address address : addresses) {
                addressMapper.updateDefault(address.getId(),false);
            }
        }
        int id = addressDetail.getId();
        Boolean aDefault = addressDetail.getIsDefault();
        addressDetail.setUserId(userId);
        // 有该地址的信息就更新，没有的话就插入
        addressMapper.updateAddress(id,addressDetail);
        int defaultId = addressMapper.queryIdByDefault();
        return defaultId;
    }

    @Override
    public List<Region> getRegionList(Integer pid) {
        RegionExample regionExample = new RegionExample();
        RegionExample.Criteria criteria = regionExample.createCriteria();
        criteria.andPidEqualTo(pid);
        List<Region> regionList = regionMapper.selectByExample(regionExample);
        return regionList;
    }

    @Override
    public int deleteAddress(Integer id) {
        int i = addressMapper.deleteByPrimaryKey(id);
        return i;
    }

    @Override
    public void orderCancel(Order order) {
        Integer id = order.getId();
        int status = 102;//用户取消
        int i = orderMapper.updateStatus(id,status);
    }

    @Override
    public void orderConfirm(Order order) {
        Integer id = order.getId();
        int status = 401;//用户收货
        int i = orderMapper.updateStatus(id,status);
    }

    @Override
    public void submitFeedback(Feedback feedback) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andIdEqualTo(feedback.getUserId());
        List<User> users = userMapper.selectByExample(userExample);
        User user = users.get(0);
        feedback.setUsername(user.getUsername());
        feedback.setStatus(3);
        int insert = feedbackMapper.insert(feedback);
    }
}
