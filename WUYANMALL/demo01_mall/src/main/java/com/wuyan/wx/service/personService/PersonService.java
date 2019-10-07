package com.wuyan.wx.service.personService;

import com.wuyan.wx.bean.UserLoginInfo;
import com.wuyan.wx.bean.UserToken;

/**
 * 个人中心业务层
 */
public interface PersonService {

    UserLoginInfo queryUser(UserToken userToken);
}
