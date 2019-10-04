package com.wuyan.mall.service.systemService;

import com.wuyan.mall.bean.Log;
import com.wuyan.mall.bean.LogExample;
import com.wuyan.mall.vo.ResultInfos;
import org.apache.ibatis.annotations.Param;

public interface LogService {
    long countByExample(LogExample example);

    int deleteByExample(LogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Log record);

    int insertSelective(Log record);

    ResultInfos selectByExample(int page, int limit, String name);

    Log selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Log record, @Param("example") LogExample example);

    int updateByExample(@Param("record") Log record, @Param("example") LogExample example);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);
}
