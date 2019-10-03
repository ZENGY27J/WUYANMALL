package com.wuyan.mall.vo;

import java.util.List;

/**
 * @Description: 商品统计返回的对象
 * @Param:
 * @return:
 * @Author: fangbo
 * @Date: 2019/10/2
 */
public class StatGoodVo {

    String[] columns;
    List<StatGoodRowVo> rows;

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public List<StatGoodRowVo> getRows() {
        return rows;
    }

    public void setRows(List<StatGoodRowVo> rows) {
        this.rows = rows;
    }
}
