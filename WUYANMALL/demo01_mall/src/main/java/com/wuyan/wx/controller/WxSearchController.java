package com.wuyan.wx.controller;

import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.wx.bean.SearchBean;
import com.wuyan.wx.service.searchService.WxSearchService;
import com.wuyan.wx.utils.GetUserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("wx/search")
public class WxSearchController {
    @Autowired
    WxSearchService wxSearchService;
    @RequestMapping("index")
    public BaseRespVo searchIndex(HttpServletRequest request){
        Integer userId = GetUserId.getUserIdByRequest(request);
        SearchBean searchBean = wxSearchService.getSearchIndex(userId);
        return BaseRespVo.ok(searchBean);
    }
    @RequestMapping("helper")
    public BaseRespVo helper(String keyword){
        List<String> kw = wxSearchService.getKeyword(keyword);
        return BaseRespVo.ok(kw);
    }
    @RequestMapping("clearhistory")
    public BaseRespVo clearHistory(HttpServletRequest request){
        Integer userId = GetUserId.getUserIdByRequest(request);
        Boolean flag = wxSearchService.clearHistory(userId);
        return BaseRespVo.ok(null);
    }
}
