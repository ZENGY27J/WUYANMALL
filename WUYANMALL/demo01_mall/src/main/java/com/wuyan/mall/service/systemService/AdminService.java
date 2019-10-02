package com.wuyan.mall.service.systemService;

import com.wuyan.mall.bean.Admin;
import com.wuyan.mall.bean.AdminExample;
import com.wuyan.mall.vo.AdminInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminService {
    long countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<AdminInfo> selectByExample(String username);

    Admin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}
