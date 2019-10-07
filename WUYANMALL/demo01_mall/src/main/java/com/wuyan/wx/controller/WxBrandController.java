package com.wuyan.wx.controller;

import com.wuyan.mall.bean.BaseData;
import com.wuyan.mall.bean.Brand;
import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.mall.vo.PageInfo;
import com.wuyan.wx.bean.Databean;
import com.wuyan.wx.service.brandService.WxBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wx/brand")
public class WxBrandController {
    @Autowired
    WxBrandService wxBrandService;

    @RequestMapping("list")
    public BaseRespVo getBrandList(PageInfo pageInfo){
        Databean databean = wxBrandService.getBrandList(pageInfo);
        return BaseRespVo.ok(databean);
    }

    @RequestMapping("detail")
    public BaseRespVo getBrandDetail(String id) {
        Brand brand = wxBrandService.getBrandDetail(Integer.parseInt(id));
        return BaseRespVo.ok(brand);
    }
}
