package com.wuyan.mall.service.systemService;

import com.wuyan.mall.bean.Role;
import com.wuyan.mall.bean.RoleExample;
import com.wuyan.mall.vo.ResultInfos;
import org.apache.ibatis.annotations.Param;

public interface RoleService {
    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    ResultInfos selectByExample(int page, int limit, String name);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}
