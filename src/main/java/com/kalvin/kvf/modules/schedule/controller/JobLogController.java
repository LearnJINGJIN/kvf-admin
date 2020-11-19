package com.kalvin.kvf.modules.schedule.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.modules.schedule.entity.JobLog;
import com.kalvin.kvf.modules.schedule.service.JobLogService;

import java.util.List;


/**
 * <p>
 * 定时任务表执行日志表 前端控制器
 * </p>
 * @since 2020-10-29 17:21:25
 */
@RestController
@RequestMapping("schedule/jobLog")
public class JobLogController extends BaseController {

    @Autowired
    private JobLogService jobLogService;

    @RequiresPermissions("schedule:jobLog:index")
    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("schedule/jobLog");
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("schedule/jobLog_edit");
        JobLog jobLog;
        if (id == null) {
            jobLog = new JobLog();
        } else {
            jobLog = jobLogService.getById(id);
        }
        mv.addObject("editInfo", jobLog);
        return mv;
    }

    @GetMapping(value = "list/data")
    public R listData(JobLog jobLog) {
        Page<JobLog> page = jobLogService.listJobLogPage(jobLog);
        return R.ok(page);
    }

    @RequiresPermissions("schedule:jobLog:edit")
    @PostMapping(value = "edit")
    public R edit(JobLog jobLog) {
        jobLogService.updateById(jobLog);
        return R.ok();
    }

    @RequiresPermissions("schedule:jobLog:del")
    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        jobLogService.removeById(id);
        return R.ok();
    }

    @GetMapping(value = "get/{id}")
    public R get(@PathVariable Long id) {
        return R.ok(jobLogService.getById(id));
    }

}

