package com.wuyan.mall.util;

import com.wuyan.mall.bean.BrandExample;
import com.wuyan.mall.bean.CategoryExample;
import com.wuyan.mall.bean.RegionExample;

public class MallUtil {

    public static RegionExample getRegion(int id){
        RegionExample regionExample = new RegionExample();
        RegionExample.Criteria criteria = regionExample.createCriteria().andPidEqualTo(id);
        return regionExample;
    }

    public static CategoryExample getCategory(Object id){
        CategoryExample categoryExample = new CategoryExample();
        if(id instanceof Integer) {
            CategoryExample.Criteria criteria = categoryExample.createCriteria().andPidEqualTo((Integer)id);
        }else if(id instanceof String){
            CategoryExample.Criteria criteria = categoryExample.createCriteria().andNameEqualTo((String)id);
        }
        return categoryExample;
    }
    public static CategoryExample getCategoryById(int id){
        CategoryExample categoryExample = new CategoryExample();
        CategoryExample.Criteria criteria = categoryExample.createCriteria().andIdEqualTo(id);
        return categoryExample;
    }

    public static BrandExample getBrandAll(int id,String name,Boolean flag){
        BrandExample brandExample = new BrandExample();
        if(!flag){
            BrandExample.Criteria criteria = brandExample.createCriteria();
        }else if(name == null || name.length()==0) {
            BrandExample.Criteria criteria = brandExample.createCriteria().andIdEqualTo(id);
        }else{
            BrandExample.Criteria criteria1 = brandExample.createCriteria().andNameLike("%" + name + "%");
            BrandExample.Criteria criteria2 = brandExample.createCriteria().andIdEqualTo(id);
            brandExample.or(criteria2);
        }
        return  brandExample;
    }

}
