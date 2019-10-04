package com.wuyan.mall.vo;

import java.util.List;

/**
 * @Description: 订单统计封装的对象
 * @Param:
 * @return:
 * @Author: fangbo
 * @Date: 2019/10/2
 */
public class StatOrderVo {
    String[] columns;

    List<StatOrderRowVo> rows;

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public List<StatOrderRowVo> getRows() {
        return rows;
    }

    public void setRows(List<StatOrderRowVo> rows) {
        this.rows = rows;
    }
}
