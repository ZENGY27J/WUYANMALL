package com.wuyan.mall.mapper;

import com.wuyan.mall.bean.Admin;
import com.wuyan.mall.bean.AdminExample;
import java.util.List;

import com.wuyan.mall.vo.AdminInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {
    long countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<AdminInfo> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
<<<<<<< HEAD

//    @Select()
    List<String> queryPermissionsByUsername(@Param("username") String username);
=======
>>>>>>> d6f0215696f85cddb69ea3101feb08d8d0932faf
}
