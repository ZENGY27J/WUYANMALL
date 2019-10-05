package com.wuyan.wx.service.brandService;

import com.github.pagehelper.PageHelper;
import com.wuyan.mall.bean.Brand;
import com.wuyan.mall.bean.BrandExample;
import com.wuyan.mall.mapper.BrandMapper;
import com.wuyan.mall.vo.PageInfo;
import com.wuyan.wx.bean.Databean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxBrandServiceImpl implements WxBrandService {
    @Autowired
    BrandMapper brandMapper;
    @Override
    public Databean getBrandList(PageInfo pageInfo) {
        BrandExample example = new BrandExample();
        long l = brandMapper.countByExample(example);
        PageHelper.startPage(pageInfo.getPage(),pageInfo.getSize());
        List<Brand> brands = brandMapper.selectByExample(example);
        Databean databean = new Databean();
        databean.setTotalPages(l / pageInfo.getSize());
        databean.setBrandList(brands);
        return databean;
    }
}
