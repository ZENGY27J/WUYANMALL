package com.wuyan.wx.service.personService;

<<<<<<< HEAD
import com.wuyan.mall.bean.Order;
import com.wuyan.mall.bean.Region;
import com.wuyan.wx.bean.*;

import java.util.List;
=======
import com.wuyan.wx.bean.UserLoginInfo;
import com.wuyan.wx.bean.UserToken;
>>>>>>> d6f0215696f85cddb69ea3101feb08d8d0932faf

/**
 * 个人中心业务层
 */
public interface PersonService {

    UserLoginInfo queryUser(UserToken userToken);
<<<<<<< HEAD

    UserOrderStatus getUserOrderStatus(Integer userId);

    OrderPageList getOrderList(OrderPage orderPage, Integer userId);

    OrderDetail getOrderDrtail(Integer orderId, Integer userId);

    List<AddressInfo> getAddressList(Integer userId);

    AddressDetail getAddressDetail(Integer id,Integer userId);

    int updateAddressDefault(AddressDetail addressDetail, Integer userId);

    List<Region> getRegionList(Integer pid);

    int deleteAddress(Integer id);

    void orderCancel(Order order);

    void orderConfirm(Order order);
=======
>>>>>>> d6f0215696f85cddb69ea3101feb08d8d0932faf
}
