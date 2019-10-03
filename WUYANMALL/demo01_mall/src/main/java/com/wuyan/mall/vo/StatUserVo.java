package com.wuyan.mall.vo;

import java.util.List;

/**
 * @Description: 用户统计封装的对象
 * @Param:
 * @return:
 * @Author: fangbo
 * @Date: 2019/10/2
 */
public class StatUserVo {
    String[] columns;

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    List<StatUserRowVo> rows;

    public List<StatUserRowVo> getRows() {
        return rows;
    }

    public void setRows(List<StatUserRowVo> rows) {
        this.rows = rows;
    }
}
