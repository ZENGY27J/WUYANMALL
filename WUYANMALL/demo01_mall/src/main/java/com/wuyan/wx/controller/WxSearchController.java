package com.wuyan.wx.controller;

import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.wx.bean.SearchBean;
import com.wuyan.wx.service.searchService.WxSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("wx/search")
public class WxSearchController {
    @Autowired
    WxSearchService wxSearchService;
    @RequestMapping("index")
    public BaseRespVo searchIndex(){
        SearchBean searchBean = wxSearchService.getSearchIndex();
        return BaseRespVo.ok(searchBean);
    }
    @RequestMapping("helper")
    public BaseRespVo helper(String keyword){
        List<String> kw = wxSearchService.getKeyword(keyword);
        return BaseRespVo.ok(kw);
    }
    @RequestMapping("clearhistory")
    public BaseRespVo clearHistory(){
        Boolean flag = wxSearchService.clearHistory();
        return BaseRespVo.ok(null);
    }
}
