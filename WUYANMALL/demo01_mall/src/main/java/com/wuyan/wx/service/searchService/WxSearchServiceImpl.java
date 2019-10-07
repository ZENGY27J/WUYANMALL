package com.wuyan.wx.service.searchService;

import com.wuyan.mall.bean.Keyword;
import com.wuyan.mall.bean.SearchHistory;
import com.wuyan.mall.mapper.KeywordMapper;
import com.wuyan.mall.mapper.SearchHistoryMapper;
import com.wuyan.wx.bean.SearchBean;
import com.wuyan.wx.utils.QueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WxSearchServiceImpl implements WxSearchService{
    @Autowired
    KeywordMapper keywordMapper;
    @Autowired
    SearchHistoryMapper searchHistoryMapper;
    @Override
    public SearchBean getSearchIndex(Integer userId) {
        SearchBean searchBean = new SearchBean();
        List<Keyword> defaultKeyword = keywordMapper.selectByExample(QueryUtils.getDefaultKeyword());
        searchBean.setDefaultKeyword(defaultKeyword.get(0));
        List<Keyword> hotKeyword = keywordMapper.selectByExample(QueryUtils.getHotKeyword());
        searchBean.setHotKeywordList(hotKeyword);
        List<SearchHistory> searchHistories = searchHistoryMapper.selectByExample(QueryUtils.getSearchHistory(userId));
        searchBean.setHistoryKeywordList(searchHistories);
        return searchBean;
    }

    @Override
    public List<String> getKeyword(String keyword) {
        List<Keyword> keywords = keywordMapper.selectByExample(QueryUtils.getLikeKeyword(keyword));
        List<String> s = new ArrayList<>();
        for (Keyword keyword1 : keywords) {
            s.add(keyword1.getKeyword());
        }
        return s;
    }

    @Override
    public Boolean clearHistory(Integer userId) {
        int i = searchHistoryMapper.deleteByExample(QueryUtils.getSearchHistory(userId));
        boolean flag = false;
        if (i != 0){
            flag = true ;
        }
        return flag;
    }
}
