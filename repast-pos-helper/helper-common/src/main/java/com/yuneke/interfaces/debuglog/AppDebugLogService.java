package com.yuneke.interfaces.debuglog;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuneke.model.debuglog.AppDebugLog;

import java.util.List;

public interface AppDebugLogService extends IService<AppDebugLog> {
    public List<AppDebugLog> selectAll();
}
