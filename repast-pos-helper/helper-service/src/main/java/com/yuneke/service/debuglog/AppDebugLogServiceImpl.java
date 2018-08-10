package com.yuneke.service.debuglog;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuneke.appdebug.mapper.AppDebugLogMapper;
import com.yuneke.interfaces.debuglog.AppDebugLogService;
import com.yuneke.model.debuglog.AppDebugLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class AppDebugLogServiceImpl extends ServiceImpl<AppDebugLogMapper, AppDebugLog> implements AppDebugLogService {
}
