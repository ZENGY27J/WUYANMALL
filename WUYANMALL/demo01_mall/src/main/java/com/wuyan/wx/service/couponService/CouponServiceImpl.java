package com.wuyan.wx.service.couponService;

import com.wuyan.mall.bean.Coupon;
import com.wuyan.mall.bean.CouponUser;
import com.wuyan.mall.mapper.CouponMapper;
import com.wuyan.mall.mapper.CouponUserMapper;
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
    @Autowired
    CouponUserMapper couponUserMapper;

    @Override
    public List<Coupon> getAllCoupons(PageInfo pageInfo) {
        return couponMapper.selectByExample(QueryUtils.getCoupon(pageInfo.getPage(), pageInfo.getSize()));
    }

    @Override
    public int couponReceive(int couponId,int userId) {
        CouponUser couponUser = new CouponUser();
        couponUser.setCouponId(couponId);
        couponUser.setUserId(userId);
        List<Coupon> coupons = couponMapper.selectByExample(QueryUtils.getCoupon(couponId));
        Integer total = coupons.get(0).getTotal();
        if (total == 0){
            couponUserMapper.insert(couponUser);
        }
        int temp = total - 1;
        if (temp == 0){
            couponUserMapper.insert(couponUser);
            couponMapper.updateTotal(-1);
            return -1;
        }
        if (temp > 0){
            couponUserMapper.insert(couponUser);
            couponMapper.updateTotal(temp);
        }
        return total;
    }

    @Override
    public int exchange(String code, Integer userId) {
        List<Coupon> coupons = couponMapper.selectByExample(QueryUtils.getCouponByCode(code));
        if (coupons.size() == 0) {
            return 0;
        }
        return couponReceive(coupons.get(0).getId(), userId);
    }
}
