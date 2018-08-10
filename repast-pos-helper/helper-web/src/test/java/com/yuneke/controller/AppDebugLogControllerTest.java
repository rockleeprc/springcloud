package com.yuneke.controller;

import com.yuneke.common.Result;
import com.yuneke.controller.debuglog.AppDebugLogController;
import com.yuneke.model.AppDebugLog;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "dev")
@SpringBootTest
public class AppDebugLogControllerTest {

    @Autowired
    private AppDebugLogController appDebugLogController;

    @Test
    public void point() {
        AppDebugLog entity = new AppDebugLog(11L, 22L, 33, "bb", new Date());
        int type=5;
        long userId=10L;
        long merchantId=20L;
        String detail = "AAA";
        Result<Boolean> result = appDebugLogController.point(userId, merchantId, type, null);
        Assert.assertTrue(Boolean.valueOf(result.getData()));
    }


    @Test
    public void test() {
        Assert.assertNotNull(appDebugLogController);
    }
}
