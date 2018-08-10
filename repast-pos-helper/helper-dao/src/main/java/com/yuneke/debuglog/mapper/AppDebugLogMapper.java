package com.yuneke.debuglog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuneke.model.debuglog.AppDebugLog;

import java.util.List;

public interface AppDebugLogMapper extends BaseMapper<AppDebugLog> {

    public List<AppDebugLog> selectAll();
}
