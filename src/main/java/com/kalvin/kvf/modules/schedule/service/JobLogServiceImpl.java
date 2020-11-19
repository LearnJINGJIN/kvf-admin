package com.kalvin.kvf.modules.schedule.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.kalvin.kvf.modules.schedule.entity.JobLog;
import com.kalvin.kvf.modules.schedule.mapper.JobLogMapper;

import java.util.List;

/**
 * <p>
 * 定时任务表执行日志表 服务实现类
 * </p>
 * @since 2020-10-29 17:21:25
 */
@Service
public class JobLogServiceImpl extends ServiceImpl<JobLogMapper, JobLog> implements JobLogService {

    @Override
    public Page<JobLog> listJobLogPage(JobLog jobLog) {
        Page<JobLog> page = new Page<>(jobLog.getCurrent(), jobLog.getSize());
        List<JobLog> jobLogs = baseMapper.selectJobLogList(jobLog, page);
        return page.setRecords(jobLogs);
    }

}
