package com.wuyan.mall.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.wuyan.mall.bean.*;
import com.wuyan.mall.bean.UserMagerBean.AddressPage;
import com.wuyan.mall.bean.UserMagerBean.UserPage;
import com.wuyan.mall.mapper.*;
import com.wuyan.mall.service.user.UserService;
import com.wuyan.mall.vo.UserPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Program: WUYANMALL
 * @Author: ZyEthan
 * @Description: 用户管理接口实现类(查找时非法字符要做校验)
 * @Date: 2019-09-30-15:57
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    RegionMapper regionMapper;
    @Autowired
    CollectMapper collectMapper;
    @Autowired
    FootprintMapper footprintMapper;
    @Autowired
    SearchHistoryMapper searchHistoryMapper;
    @Autowired
    FeedbackMapper feedbackMapper;


    /**
     * 查询用户列表
     *
     * @param pageInfo
     * @return
     */
    @Override
    public UserPage getUserPage(UserPageInfo pageInfo) {
        UserPage userPage = new UserPage();
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        PageHelper.startPage(pageInfo.getPage(), pageInfo.getLimit());
        PageHelper.orderBy(pageInfo.getSort());
        List<User> users = null;
        long total = 0;
        // 查找：条件返回
        if (pageInfo.getUsername() == null && pageInfo.getMobile() == null ||
                pageInfo.getUsername() == "" && pageInfo.getMobile() == "") {
            users = userMapper.selectByExample(userExample);
            total = userMapper.countByExample(userExample);
        } else if (pageInfo.getUsername() == null && pageInfo.getMobile() != null) {
            criteria.andMobileEqualTo(pageInfo.getMobile());
            users = userMapper.selectByExample(userExample);
            total = userMapper.countByExample(userExample);
        } else if (pageInfo.getUsername() != null && pageInfo.getMobile() == null) {
            criteria.andUsernameLike("%" + pageInfo.getUsername() + "%");
            users = userMapper.selectByExample(userExample);
            total = userMapper.countByExample(userExample);
        } else {
            criteria.andMobileEqualTo(pageInfo.getMobile());
            criteria.andUsernameLike("%" + pageInfo.getUsername() + "%");
            users = userMapper.selectByExample(userExample);
            total = userMapper.countByExample(userExample);
        }
        userPage.setTotal(total);
        userPage.setItems(users);
        return userPage;
    }

    /**
     * 查询收获地址
     * @param pageInfo
     * @return
     */
    @Override
    public AddressPage getAddressPage(UserPageInfo pageInfo) {
        AddressPage addressPage = new AddressPage();
        AddressExample addressExample = new AddressExample();
        AddressExample.Criteria criteria = addressExample.createCriteria();
        PageHelper.startPage(pageInfo.getPage(), pageInfo.getLimit());
        PageHelper.orderBy(pageInfo.getSort());
        List<Address> addresses = null;
        long total = 0;
        //查找：条件返回
        int userId = 0;
        String regex = "^[0-9]*$";
        if (pageInfo.getUserId() != null && pageInfo.getUserId() != "") {
            if (!pageInfo.getUserId().matches(regex)) {
                return null;
            }
             userId= Integer.valueOf(pageInfo.getUserId());
        }
        if (userId == 0 && pageInfo.getName() == null) {
            addresses = addressMapper.selectByExample(addressExample);
            total = addressMapper.countByExample(addressExample);
        } else if (userId != 0 && pageInfo.getName() == null) {
            criteria.andUserIdEqualTo(userId);
            addresses = addressMapper.selectByExample(addressExample);
            total = addressMapper.countByExample(addressExample);
        } else if (userId == 0 && pageInfo.getName() != null) {
            criteria.andNameLike("%" + pageInfo.getName() + "%");
            addresses = addressMapper.selectByExample(addressExample);
            total = addressMapper.countByExample(addressExample);
        } else {
            criteria.andUserIdEqualTo(userId);
            criteria.andNameLike("%" + pageInfo.getName() + "%");
            addresses = addressMapper.selectByExample(addressExample);
            total = addressMapper.countByExample(addressExample);
        }
        // 查询省份城市表得到具体名称
        for (Address address : addresses) {
            Integer areaId = address.getAreaId();
            RegionExample regionExample = new RegionExample();
            RegionExample.Criteria regionExampleCriteria = regionExample.createCriteria();
            regionExampleCriteria.andCodeEqualTo(areaId);
            List<Region> regions = regionMapper.selectByExample(regionExample);
            address.setArea(regions.get(0).getName());
        }
        for (Address address : addresses) {
            Integer cityId = address.getCityId();
            RegionExample regionExample = new RegionExample();
            RegionExample.Criteria regionExampleCriteria = regionExample.createCriteria();
            regionExampleCriteria.andCodeEqualTo(cityId);
            List<Region> regions = regionMapper.selectByExample(regionExample);
            address.setCity(regions.get(0).getName());
        }
        for (Address address : addresses) {
            Integer provinceId = address.getProvinceId();
            RegionExample regionExample = new RegionExample();
            RegionExample.Criteria regionExampleCriteria = regionExample.createCriteria();
            regionExampleCriteria.andCodeEqualTo(provinceId);
            List<Region> regions = regionMapper.selectByExample(regionExample);
            address.setProvince(regions.get(0).getName());
        }
        addressPage.setTotal(total);
        addressPage.setItems(addresses);
        return addressPage;
    }

    @Override
    public BaseData<Collect> getUserCollect(UserPageInfo pageInfo) {
        BaseData<Collect> collectBaseData = new BaseData<>();
        CollectExample collectExample = new CollectExample();
        CollectExample.Criteria criteria = collectExample.createCriteria();
        PageHelper.startPage(pageInfo.getPage(), pageInfo.getLimit());
        PageHelper.orderBy(pageInfo.getSort());
        List<Collect> collects = null;
        long total = 0;
        //查找：条件返回
        int userId = 0;
        int valueId = 0;
        String regex = "^[0-9]*$";
        if (pageInfo.getUserId() != null && pageInfo.getUserId() != "") {
            if (!pageInfo.getUserId().matches(regex)) {
                return null;
            }
            userId= Integer.valueOf(pageInfo.getUserId());
        }
        if (pageInfo.getValueId() != null && pageInfo.getValueId() != "") {
            if (!pageInfo.getValueId().matches(regex)) {
                return null;
            }
            valueId= Integer.valueOf(pageInfo.getValueId());
        }
        if (userId == 0 && valueId == 0) {
            collects = collectMapper.selectByExample(collectExample);
            total = collectMapper.countByExample(collectExample);
        } else if (userId != 0 && valueId == 0) {
            criteria.andUserIdEqualTo(userId);
            collects = collectMapper.selectByExample(collectExample);
            total = collectMapper.countByExample(collectExample);
        } else if (userId == 0 && valueId != 0) {
            criteria.andValueIdEqualTo(valueId);
            collects = collectMapper.selectByExample(collectExample);
            total = collectMapper.countByExample(collectExample);
        } else {
            criteria.andUserIdEqualTo(userId);
            criteria.andValueIdEqualTo(valueId);
            collects = collectMapper.selectByExample(collectExample);
            total = collectMapper.countByExample(collectExample);
        }
        collectBaseData.setTotal(total);
        collectBaseData.setItems(collects);
        return collectBaseData;
    }

    @Override
    public BaseData<Footprint> getFootprint(UserPageInfo pageInfo) {
        BaseData<Footprint> footprintBaseData = new BaseData<>();
        FootprintExample footprintExample = new FootprintExample();
        FootprintExample.Criteria criteria = footprintExample.createCriteria();
        PageHelper.startPage(pageInfo.getPage(), pageInfo.getLimit());
        PageHelper.orderBy(pageInfo.getSort());
        List<Footprint> footprints = null;
        long total = 0;
        //查找：条件返回
        int userId = 0;
        int goodsId = 0;
        String regex = "^[0-9]*$";
        if (pageInfo.getUserId() != null && pageInfo.getUserId() != "") {
            if (!pageInfo.getUserId().matches(regex)) {
                return null;
            }
            userId= Integer.valueOf(pageInfo.getUserId());
        }
        if (pageInfo.getGoodsId() != null && pageInfo.getGoodsId() != "") {
            if (!pageInfo.getGoodsId().matches(regex)) {
                return null;
            }
            goodsId= Integer.valueOf(pageInfo.getGoodsId());
        }
        if (userId == 0 && goodsId == 0) {
            footprints = footprintMapper.selectByExample(footprintExample);
            total = footprintMapper.countByExample(footprintExample);
        } else if (userId != 0 && goodsId == 0) {
            criteria.andUserIdEqualTo(userId);
            footprints = footprintMapper.selectByExample(footprintExample);
            total = footprintMapper.countByExample(footprintExample);
        } else if (userId == 0 && goodsId != 0) {
            criteria.andGoodsIdEqualTo(goodsId);
            footprints = footprintMapper.selectByExample(footprintExample);
            total = footprintMapper.countByExample(footprintExample);
        } else {
            criteria.andUserIdEqualTo(userId);
            criteria.andGoodsIdEqualTo(goodsId);
            footprints = footprintMapper.selectByExample(footprintExample);
            total = footprintMapper.countByExample(footprintExample);
        }
        footprintBaseData.setTotal(total);
        footprintBaseData.setItems(footprints);
        return footprintBaseData;
    }

    @Override
    public BaseData<SearchHistory> getHistory(UserPageInfo pageInfo) {
        BaseData<SearchHistory> searchHistoryBaseData = new BaseData<>();
        SearchHistoryExample searchHistoryExample = new SearchHistoryExample();
        SearchHistoryExample.Criteria criteria = searchHistoryExample.createCriteria();
        PageHelper.startPage(pageInfo.getPage(), pageInfo.getLimit());
        PageHelper.orderBy(pageInfo.getSort());
        List<SearchHistory> searchHistories = null;
        long total = 0;
        //查找：条件返回
        int userId = 0;
        String keyword = null;
        String regex = "^[0-9]*$";
        if (pageInfo.getUserId() != null && pageInfo.getUserId() != "") {
            if (!pageInfo.getUserId().matches(regex)) {
                return null;
            }
            userId= Integer.valueOf(pageInfo.getUserId());
        }
        if (pageInfo.getKeyword() != null && pageInfo.getKeyword() != "") {
            keyword= pageInfo.getKeyword();
        }

        if (userId == 0 && keyword == null) {
            total = searchHistoryMapper.countByExample(searchHistoryExample);
            searchHistories = searchHistoryMapper.selectByExample(searchHistoryExample);
        }else if (userId != 0 && keyword == null) {
            criteria.andUserIdEqualTo(userId);
            searchHistories = searchHistoryMapper.selectByExample(searchHistoryExample);
            total = searchHistoryMapper.countByExample(searchHistoryExample);
        } else if (userId == 0 && keyword != null) {
            criteria.andKeywordLike("%" + keyword + "%");
            searchHistories = searchHistoryMapper.selectByExample(searchHistoryExample);
            total = searchHistoryMapper.countByExample(searchHistoryExample);
        } else {
            criteria.andUserIdEqualTo(userId);
            criteria.andKeywordLike("%" + keyword + "%");
            searchHistories = searchHistoryMapper.selectByExample(searchHistoryExample);
            total = searchHistoryMapper.countByExample(searchHistoryExample);
        }
        searchHistoryBaseData.setTotal(total);
        searchHistoryBaseData.setItems(searchHistories);
        return searchHistoryBaseData;
    }


    @Override
    public BaseData<Feedback> getFeedback(UserPageInfo pageInfo) {
        BaseData<Feedback> feedbackBaseData = new BaseData<>();
        FeedbackExample feedbackExample  = new FeedbackExample();
        FeedbackExample.Criteria criteria = feedbackExample.createCriteria();
        PageHelper.startPage(pageInfo.getPage(), pageInfo.getLimit());
        PageHelper.orderBy(pageInfo.getSort());
        List<Feedback> feedbacks = null;
        long total = 0;
        //查找：条件返回
        int feedbackId = 0;
        String username = null;
        // 判断是否有非法字符
        String regex = "^[0-9]*$";
        if (pageInfo.getId() != null && pageInfo.getId() != "") {
            if (!pageInfo.getId().matches(regex)) {
                return null;
            }
            feedbackId= Integer.valueOf(pageInfo.getId());
        }
        if (pageInfo.getUsername() != null && pageInfo.getUsername() != "") {
            username= pageInfo.getUsername();
        }
        if (feedbackId == 0 && username == null) {
            total = feedbackMapper.countByExample(feedbackExample);
            feedbacks = feedbackMapper.selectByExample(feedbackExample);
        }else if (feedbackId != 0 && username == null) {
            criteria.andIdEqualTo(feedbackId);
            feedbacks = feedbackMapper.selectByExample(feedbackExample);
            total = feedbackMapper.countByExample(feedbackExample);
        } else if (feedbackId == 0 && username != null) {
            criteria.andUsernameLike("%" + username + "%");
            feedbacks = feedbackMapper.selectByExample(feedbackExample);
            total = feedbackMapper.countByExample(feedbackExample);
        } else {
            criteria.andIdEqualTo(feedbackId);
            criteria.andUsernameLike("%" + username + "%");
            feedbacks = feedbackMapper.selectByExample(feedbackExample);
            total = feedbackMapper.countByExample(feedbackExample);
        }
        feedbackBaseData.setTotal(total);
        feedbackBaseData.setItems(feedbacks);
        return feedbackBaseData;
    }
}
