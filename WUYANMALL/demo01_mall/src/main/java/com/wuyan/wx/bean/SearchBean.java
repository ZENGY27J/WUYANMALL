package com.wuyan.wx.bean;

import com.wuyan.mall.bean.Keyword;
import com.wuyan.mall.bean.SearchHistory;

import java.util.List;


public class SearchBean {
    Keyword defaultKeyword;
    List<SearchHistory> historyKeywordList;
    List<Keyword> hotKeywordList;

    public Keyword getDefaultKeyword() {
        return defaultKeyword;
    }

    public void setDefaultKeyword(Keyword defaultKeyword) {
        this.defaultKeyword = defaultKeyword;
    }

    public List<SearchHistory> getHistoryKeywordList() {
        return historyKeywordList;
    }

    public void setHistoryKeywordList(List<SearchHistory> historyKeywordList) {
        this.historyKeywordList = historyKeywordList;
    }

    public List<Keyword> getHotKeywordList() {
        return hotKeywordList;
    }

    public void setHotKeywordList(List<Keyword> hotKeywordList) {
        this.hotKeywordList = hotKeywordList;
    }
}
