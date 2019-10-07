package com.wuyan.wx.service.personService.impl;

import com.wuyan.mall.bean.User;
import com.wuyan.mall.bean.UserExample;
import com.wuyan.mall.mapper.UserMapper;
import com.wuyan.wx.bean.UserLoginInfo;
import com.wuyan.wx.bean.UserToken;
import com.wuyan.wx.service.personService.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Override
    public UserLoginInfo queryUser(UserToken userToken) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(userToken.getUsername()).andPasswordEqualTo(userToken.getPassword());
        List<User> users = userMapper.selectByExample(userExample);
        User user = users.get(0);
        HashMap<String,String> userInfo = new HashMap<>();
        userInfo.put("nickName",user.getNickname());
        userInfo.put("avatarUrl",user.getAvatar());
        UserLoginInfo userLoginInfo = new UserLoginInfo();
        userLoginInfo.setTokenExpire(user.getUpdateTime().toString());
        userLoginInfo.setUserInfo(userInfo);
        return userLoginInfo;
    }
}
