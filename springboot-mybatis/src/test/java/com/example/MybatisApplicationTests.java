package com.example;

import com.example.domain.AppDebugLog;
import com.example.mapper.AppDebugLogMapper;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private AppDebugLogMapper appDebugLogMapper;

    @Test
    public void pageHelper() {
        PageHelper.startPage(2, 2);
        List<AppDebugLog> list = appDebugLogMapper.selectAll();
        for (AppDebugLog log : list) {
            System.out.println(log.getDetail());
        }
    }

    @Test
    public void appDebugLogMapper() {
        AppDebugLog appDebugLog = appDebugLogMapper.selectByPrimaryKey(16L);
        System.out.println(appDebugLog.getDetail());
        System.out.println(appDebugLog.getCreateTime());
    }

    @Test
    public void sqlSession() {
        System.out.println(sqlSession);
    }

    @Test
    public void dataSource() {
        System.out.println(dataSource.getClass());
    }

    @Test
    public void contextLoads() {
    }

}
