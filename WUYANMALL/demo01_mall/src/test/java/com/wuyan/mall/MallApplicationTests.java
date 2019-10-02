package com.wuyan.mall;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wuyan.mall.bean.Log;
import com.wuyan.mall.bean.LogExample;
import com.wuyan.mall.mapper.LogMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MallApplicationTests {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date date;

    @Test
    public void contextLoads() {
        date = new Date();
        System.out.println(date);
    }
}
