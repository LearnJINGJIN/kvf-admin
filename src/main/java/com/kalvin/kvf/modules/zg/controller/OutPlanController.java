package com.kalvin.kvf.modules.zg.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.common.utils.ShiroKit;
import com.kalvin.kvf.modules.zg.entity.MattersDetail;
import com.kalvin.kvf.modules.zg.entity.OutPlan;
import com.kalvin.kvf.modules.zg.service.MattersDetailService;
import com.kalvin.kvf.modules.zg.service.OutPlanService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


/**
 * <p>
 * 年度外包计划表 前端控制器
 * </p>
 * @since 2021-01-05 10:54:44
 */
@RestController
@RequestMapping("zg/outPlan")
public class OutPlanController extends BaseController {

    @Autowired
    private OutPlanService outPlanService;
    @Autowired
    private MattersDetailService mattersDetailService;
    @RequiresPermissions("zg:outPlan:index")
    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("zg/outPlan");
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("zg/outPlan_edit");
        OutPlan outPlan;
        if (id == null) {
            outPlan = new OutPlan();
        } else {
            outPlan = outPlanService.getById(id);
        }
        mv.addObject("editInfo", outPlan);
        return mv;
    }


    @PostMapping(value = "list/data")
    public R listData(OutPlan outPlan) {
        Page<OutPlan> page = outPlanService.listOutPlanPage(outPlan);
        return R.ok(page);
    }

    @RequiresPermissions("zg:outPlan:add")
    @PostMapping(value = "add")
    public R add(OutPlan outPlan) {
        outPlan.setCreateUser(ShiroKit.getUserId());
        outPlanService.save(outPlan);
        return R.ok();

    }

    @RequiresPermissions("zg:outPlan:edit")
    @PostMapping(value = "edit")
    public R edit(OutPlan outPlan) {
        outPlan.setUpdateUser(ShiroKit.getUserId());
        outPlanService.updateById(outPlan);
        return R.ok();
    }

    @RequiresPermissions("zg:outPlan:del")
    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        outPlanService.removeById(id);
        return R.ok();
    }

    @GetMapping(value = "get/{id}")
    public R get(@PathVariable Long id) {
        return R.ok(outPlanService.getById(id));
    }
    @GetMapping(value = "addDetail")
    public ModelAndView addDetail(Long planId) {
        ModelAndView mv = new ModelAndView("zg/mattersDetail_edit");
        MattersDetail mattersDetail=new MattersDetail();
        mattersDetail.setPlanId(planId);
        mv.addObject("editInfo", mattersDetail);
        return mv;
    }
    @PostMapping(value = "editDetail")
    public R addDetail(MattersDetail mattersDetail) {
        mattersDetail.setUpdateUser(ShiroKit.getUserId());
        mattersDetailService.updateById(mattersDetail);
        return R.ok();
    }
    @GetMapping(value = "list/detailData")
    public R detailData(MattersDetail mattersDetail) {
        Page<MattersDetail> page = mattersDetailService.listMattersDetailPage(mattersDetail);
        return R.ok(page);
    }
}

