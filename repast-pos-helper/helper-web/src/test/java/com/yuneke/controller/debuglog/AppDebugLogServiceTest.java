package com.yuneke.controller.debuglog;

import com.yuneke.interfaces.debuglog.AppDebugLogService;
import com.yuneke.model.debuglog.AppDebugLog;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppDebugLogServiceTest {

    @Autowired
    private AppDebugLogService appDebugLogService;

    @Test
    public void selectAll() {
        List<AppDebugLog> list = appDebugLogService.selectAll();
        Assert.assertTrue(list.isEmpty());

        for (AppDebugLog log : list) {
            System.out.println(log.getDetail());
        }
    }
}
