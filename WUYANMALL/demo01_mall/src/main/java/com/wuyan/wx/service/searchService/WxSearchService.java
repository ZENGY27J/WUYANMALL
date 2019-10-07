package com.wuyan.wx.service.searchService;

import com.wuyan.wx.bean.SearchBean;

import java.util.List;

public interface WxSearchService {
    SearchBean getSearchIndex(Integer userId);

    List<String> getKeyword(String keyword);

    Boolean clearHistory(Integer userId);

}
