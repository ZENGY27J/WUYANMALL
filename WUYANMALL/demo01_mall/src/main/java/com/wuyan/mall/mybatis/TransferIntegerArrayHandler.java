package com.wuyan.mall.mybatis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(Integer[].class)
public class TransferIntegerArrayHandler implements TypeHandler<Integer[]> {
    ObjectMapper objectMapper = new ObjectMapper();

    //插入数据
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Integer[] integers, JdbcType jdbcType) throws SQLException {
        try{
            String jsonArray = objectMapper.writeValueAsString(integers);
            preparedStatement.setString(i,jsonArray);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }

    //查询
    @Override
    public Integer[] getResult(ResultSet resultSet, String s) throws SQLException {
        String value = resultSet.getString(s);

        return parseStringToIntegerArray(value);
    }

    private Integer[] parseStringToIntegerArray(String value) {
        Integer[] integers = new Integer[0];
        if(value == null){
            return integers;
        }
        try{
            integers = objectMapper.readValue(value,Integer[].class);
        }catch (IOException e){
            e.printStackTrace();
        }
        return integers;
    }

    @Override
    public Integer[] getResult(ResultSet resultSet, int i) throws SQLException {
        String value = resultSet.getString(i);
        return parseStringToIntegerArray(value);
    }

    @Override
    public Integer[] getResult(CallableStatement callableStatement, int i) throws SQLException {
        String value = callableStatement.getString(i);
        return parseStringToIntegerArray(value);
    }
}
