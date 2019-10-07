package com.wuyan.mall.mapper;

import com.wuyan.mall.bean.Address;
import com.wuyan.mall.bean.AddressExample;
import java.util.List;

import com.wuyan.mall.vo.BaseRespVo;
import com.wuyan.wx.bean.AddressDetail;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface AddressMapper {
    long countByExample(AddressExample example);

    int deleteByExample(AddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Address record);

    int insertSelective(Address record);

    List<Address> selectByExample(AddressExample example);

    Address selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Address record, @Param("example") AddressExample example);

    int updateByExample(@Param("record") Address record, @Param("example") AddressExample example);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

    @Select("update cskaoyan_mall_address set is_default = #{default} where id = #{id}")
    void updateDefault(@Param("id") int id,@Param("default") Boolean aDefault);

    @Select("select code from cskaoyan_mall_region where id = #{id}")
    int queryCodeById(@Param("id") Integer id);

    @Select("select id from cskaoyan_mall_address where is_default = true")
    int queryIdByDefault();

    @Select("update cskaoyan_mall_address  set is_default = #{addressDetail.isDefault}," +
            "name = #{addressDetail.name},user_id = #{addressDetail.userId},province_id = #{addressDetail.provinceId}," +
            "city_id = #{addressDetail.cityId}, area_id = #{addressDetail.areaId}, address = #{addressDetail.address} where id = #{id}")
    void updateAddress(@Param("id") int id, @Param("addressDetail") AddressDetail addressDetail);
}
