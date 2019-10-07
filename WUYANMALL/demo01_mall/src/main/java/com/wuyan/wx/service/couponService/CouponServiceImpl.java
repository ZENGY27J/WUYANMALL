package com.wuyan.wx.service.couponService;

import com.wuyan.mall.bean.Coupon;
import com.wuyan.mall.mapper.CouponMapper;
import com.wuyan.mall.vo.PageInfo;
import com.wuyan.wx.utils.QueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    CouponMapper couponMapper;

    @Override
    public List<Coupon> getAllCoupons(PageInfo pageInfo) {
        return couponMapper.selectByExample(QueryUtils.getCoupon(pageInfo.getPage(), pageInfo.getSize()));
    }
}
