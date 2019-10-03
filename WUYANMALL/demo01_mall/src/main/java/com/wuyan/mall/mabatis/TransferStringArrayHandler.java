package com.wuyan.mall.mabatis;

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

@MappedTypes(String[].class)
public class TransferStringArrayHandler implements TypeHandler<String[]> {
        ObjectMapper objectMapper = new ObjectMapper();
    /*主要针对insert方法，javabean转为数据库接收的类型*/
    @Override
    public void setParameter(PreparedStatement preparedStatement, int index, String[] strings, JdbcType jdbcType) throws SQLException {

        try {
            String jsonArray = objectMapper.writeValueAsString(strings);
            preparedStatement.setString(index,jsonArray);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
    /*下面三个方法，都是从数据库查询到javabean*/
    @Override
    public String[] getResult(ResultSet resultSet, String parameterName) throws SQLException {
        String value = resultSet.getString(parameterName);

        return parseString2StringArray(value);
    }

    @Override
    public String[] getResult(ResultSet resultSet, int index) throws SQLException {
        String value = resultSet.getString(index);
        return parseString2StringArray(value);
    }


    @Override
    public String[] getResult(CallableStatement callableStatement, int index) throws SQLException {
        String value = callableStatement.getString(index);
        return parseString2StringArray(value);
    }
    private String[] parseString2StringArray(String value) {
        String[] strings=new String[0];
        if (value == null){
            return strings;
        }
        try {
            strings = objectMapper.readValue(value, String[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }
}
