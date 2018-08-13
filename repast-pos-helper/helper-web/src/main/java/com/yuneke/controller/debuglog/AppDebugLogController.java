package com.yuneke.controller.debuglog;

import com.yuneke.common.Result;
import com.yuneke.interfaces.debuglog.AppDebugLogService;
import com.yuneke.model.debuglog.AppDebugLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/log")
public class AppDebugLogController {

    @Autowired
    private AppDebugLogService appDebugLogService;

    @RequestMapping("/point")
    public Result<Boolean> point(Long userId, Long merchantId, Integer type, String detail) {
        AppDebugLog appDebugLog = new AppDebugLog(userId, merchantId, type, detail, new Date());
        boolean result = appDebugLogService.insert(appDebugLog);
        return Result.ok(result);
    }

    @RequestMapping("/date")
    public Result<Date> date(){
        return Result.ok(new Date());
    }
}
