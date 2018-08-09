package com.yuneke.controller.debuglog;

import com.yuneke.debuglog.service.AppDebugLogService;
import com.yuneke.model.AppDebugLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class AppDebugLogController {

    @Autowired
    private AppDebugLogService appDebugLogService;

    @RequestMapping("/point")
    public boolean point(AppDebugLog appDebugLog) {
        return appDebugLogService.insert(appDebugLog);
    }

    public void point1(Integer type, Long userId, Long merchantId, String detail) {

    }
}
