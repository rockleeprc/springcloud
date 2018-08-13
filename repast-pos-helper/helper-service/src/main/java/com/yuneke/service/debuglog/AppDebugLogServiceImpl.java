package com.yuneke.service.debuglog;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuneke.debuglog.mapper.AppDebugLogMapper;
import com.yuneke.interfaces.debuglog.AppDebugLogService;
import com.yuneke.model.debuglog.AppDebugLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AppDebugLogServiceImpl extends ServiceImpl<AppDebugLogMapper, AppDebugLog> implements AppDebugLogService {
    @Autowired
    private AppDebugLogMapper appDebugLogMapper;

    @Override
    public List<AppDebugLog> selectAll() {
        return appDebugLogMapper.selectAll();
    }
}
