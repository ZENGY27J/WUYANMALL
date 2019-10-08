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

    @Select("SELECT DISTINCT p.perm FROM j16_user_t u \n" +
            "LEFT JOIN cskaoyan_mall_admin_role ur on u.id =ur.user_id\n" +
            "LEFT JOIN cskaoyan_mall_role_perm rp on ur.role_id = rp.role_id\n" +
            "left join cskaoyan_mall_permission p on rp.perm_id = p.id\n" +
            "\n" +
            "where u.username= #{username}")
    List<String> queryPermissionsByUsername(@Param("username") String username);

    @Select("select password from cskaoyan_mall_admin where username = #{username}")
    String queryPasswordByUsername(@Param("username") String principal);
}
