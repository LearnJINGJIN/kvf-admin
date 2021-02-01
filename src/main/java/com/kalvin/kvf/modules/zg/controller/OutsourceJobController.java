package com.kalvin.kvf.modules.zg.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kalvin.kvf.common.utils.ShiroKit;
import com.kalvin.kvf.common.utils.SnowflakeIdWorker;
import com.kalvin.kvf.modules.zg.entity.OutsourceProject;
import com.kalvin.kvf.modules.zg.service.OutsourceProjectService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.modules.zg.entity.OutsourceJob;
import com.kalvin.kvf.modules.zg.service.OutsourceJobService;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 项目任务清单 前端控制器
 * </p>
 * @since 2021-01-14 17:26:45
 */
@RestController
@RequestMapping("zg/outsourceJob")
public class OutsourceJobController extends BaseController {
    @Autowired
    private OutsourceProjectService outsourceProjectService;
    @Autowired
    private OutsourceJobService outsourceJobService;

    @RequiresPermissions("zg:outsourceJob:index")
    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("zg/outsourceJob");
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("zg/outsourceJob_edit");
        OutsourceJob outsourceJob;
        if (id == null) {
            outsourceJob = new OutsourceJob();
            outsourceJob.setJobCode("job"+ SnowflakeIdWorker.generateId());
        } else {
            outsourceJob = outsourceJobService.getOutsourceJobById(id);
        }
        mv.addObject("editInfo", outsourceJob);
        return mv;
    }

    @PostMapping(value = "list/data")
    public R listData(OutsourceJob outsourceJob) {
        Page<OutsourceJob> page = outsourceJobService.listOutsourceJobPage(outsourceJob);
        return R.ok(page);
    }

    @PostMapping(value = "add")
    public R add(OutsourceJob outsourceJob) {
        outsourceJobService.save(outsourceJob);
        return R.ok();
    }

    @PostMapping(value = "edit")
    public R edit(OutsourceJob outsourceJob) {
        outsourceJobService.updateById(outsourceJob);
        return R.ok();
    }

    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        outsourceJobService.removeById(id);
        return R.ok();
    }

    @GetMapping(value = "get/{id}")
    public R get(@PathVariable Long id) {
        return R.ok(outsourceJobService.getById(id));
    }

    @PostMapping(value = "addJob")
    public R addJob(Long proId) {
        List<OutsourceJob> listJob=new ArrayList<>();
        OutsourceJob job=null;
        job=new OutsourceJob();
        job.setJobCode("job"+ SnowflakeIdWorker.generateId());
        job.setJobName("外包项目立项前是否开展风险评估（应包括外包战略的一致性评价）");
        job.setProId(proId);
        job.setJobStatus(0);
        job.setCreateUser(ShiroKit.getUserId());
        listJob.add(job);
        job=new OutsourceJob();
        job.setJobCode("job"+ SnowflakeIdWorker.generateId());
        job.setJobName("（对非驻场外包项目）最近一次开展现场检查的时间（年月）");
        job.setProId(proId);
        job.setJobStatus(0);
        job.setCreateUser(ShiroKit.getUserId());
        listJob.add(job);
        job=new OutsourceJob();
        job.setJobCode("job"+ SnowflakeIdWorker.generateId());
        job.setJobName("外包合同中的保密协议情况");
        job.setProId(proId);
        job.setJobStatus(0);
        job.setCreateUser(ShiroKit.getUserId());
        listJob.add(job);
        job=new OutsourceJob();
        job.setJobCode("job"+ SnowflakeIdWorker.generateId());
        job.setJobName("外包合同中的服务水平约定");
        job.setProId(proId);
        job.setJobStatus(0);
        job.setCreateUser(ShiroKit.getUserId());
        listJob.add(job);
        job=new OutsourceJob();
        job.setJobCode("job"+ SnowflakeIdWorker.generateId());
        job.setJobName("外包合同中对外包服务商的约束条框");
        job.setProId(proId);
        job.setJobStatus(0);
        job.setCreateUser(ShiroKit.getUserId());
        listJob.add(job);
        job=new OutsourceJob();
        job.setJobCode("job"+ SnowflakeIdWorker.generateId());
        job.setJobName("对外包服务过程和质量进行监控的措施");
        job.setProId(proId);
        job.setJobStatus(0);
        job.setCreateUser(ShiroKit.getUserId());
        listJob.add(job);
        job=new OutsourceJob();
        job.setJobCode("job"+ SnowflakeIdWorker.generateId());
        job.setJobName("对外包服务中可能出现的重大缺失（异常中断、终止和退出等）建立的应急预案");
        job.setProId(proId);
        job.setJobStatus(0);
        job.setCreateUser(ShiroKit.getUserId());
        listJob.add(job);
        job=new OutsourceJob();
        job.setJobCode("job"+ SnowflakeIdWorker.generateId());
        job.setJobName("向监管部门报告的情况");
        job.setProId(proId);
        job.setJobStatus(0);
        job.setCreateUser(ShiroKit.getUserId());
        listJob.add(job);
        boolean b = outsourceJobService.saveBatch(listJob);
        if (b){
            OutsourceProject outPro=new OutsourceProject();
            outPro.setId(proId);
            outPro.setIsJob(1);
            outsourceProjectService.updateById(outPro);
        }
        return R.ok();
    }
}

