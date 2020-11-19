package com.kalvin.kvf.modules.schedule.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kalvin.kvf.modules.schedule.entity.JobLog;

/**
 * <p>
 * 定时任务表执行日志表 服务类
 * </p>
 * @since 2020-10-29 17:21:25
 */
public interface JobLogService extends IService<JobLog> {

    /**
     * 获取列表。分页
     * @param jobLog 查询参数
     * @return page
     */
    Page<JobLog> listJobLogPage(JobLog jobLog);

}
