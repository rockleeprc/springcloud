package com.yuneke.controller.debuglog;

import com.yuneke.common.Result;
import com.yuneke.interfaces.debuglog.AppDebugLogService;
import com.yuneke.model.debuglog.AppDebugLog;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.swing.StringUIClientPropertyKey;

import java.util.Date;

@RestController
@RequestMapping("/log")
public class AppDebugLogController {

    private Logger logger = LoggerFactory.getLogger(AppDebugLogController.class);

    @Autowired
    private AppDebugLogService appDebugLogService;

    @RequestMapping("/point")
    public Result<Boolean> point(Long userId, Long merchantId, Integer type, String detail) {

        if(userId==null || merchantId==null || type==null || StringUtils.isEmpty(detail)){
            return Result.ok(false);
        }

        logger.info("userId="+userId);
        logger.info("merchantId="+merchantId);
        logger.info("type="+type);
        logger.info("detail="+detail);
        AppDebugLog appDebugLog = new AppDebugLog(userId, merchantId, type, detail, new Date());
        boolean result = appDebugLogService.insert(appDebugLog);
        return Result.ok(result);
    }

    @RequestMapping("/date")
    public Result<Date> date(){
        return Result.ok(new Date());
    }
}
