package com.yuneke.service.debuglog;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuneke.appdebug.mapper.AppDebugLogMapper;
import com.yuneke.interfaces.debuglog.AppDebugLogService;
import com.yuneke.model.AppDebugLog;
import org.springframework.stereotype.Service;

@Service
public class AppDebugLogServiceImpl extends ServiceImpl<AppDebugLogMapper, AppDebugLog> implements AppDebugLogService {
}
