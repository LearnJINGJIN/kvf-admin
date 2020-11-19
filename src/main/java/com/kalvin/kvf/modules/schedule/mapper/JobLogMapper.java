package com.kalvin.kvf.modules.schedule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kalvin.kvf.modules.schedule.entity.JobLog;

import java.util.List;

/**
 * <p>
 * 定时任务表执行日志表 Mapper 接口
 * </p>
 * @since 2020-10-29 17:21:25
 */
public interface JobLogMapper extends BaseMapper<JobLog> {

    /**
     * 查询列表(分页)
     * @param jobLog 查询参数
     * @param page 分页参数
     * @return list
     */
    List<JobLog> selectJobLogList(JobLog jobLog, IPage page);

}
