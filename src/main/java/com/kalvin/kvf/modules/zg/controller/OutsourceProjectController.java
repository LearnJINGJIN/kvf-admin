package com.kalvin.kvf.modules.zg.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.modules.zg.entity.OutsourceProject;
import com.kalvin.kvf.modules.zg.service.OutsourceProjectService;

import java.util.List;


/**
 * <p>
 * 外包项目清单 前端控制器
 * </p>
 * @since 2021-01-14 09:59:41
 */
@RestController
@RequestMapping("zg/outsourceProject")
public class OutsourceProjectController extends BaseController {

    @Autowired
    private OutsourceProjectService outsourceProjectService;

    @RequiresPermissions("zg:outsourceProject:index")
    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("zg/outsourceProject");
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("zg/outsourceProject_edit");
        OutsourceProject outsourceProject;
        if (id == null) {
            outsourceProject = new OutsourceProject();
        } else {
            outsourceProject = outsourceProjectService.getOutsourceProjectById(id);
        }
        mv.addObject("editInfo", outsourceProject);
        return mv;
    }

    @PostMapping(value = "list/data")
    public R listData(OutsourceProject outsourceProject) {
        Page<OutsourceProject> page = outsourceProjectService.listOutsourceProjectPage(outsourceProject);
        return R.ok(page);
    }

    @RequiresPermissions("zg:outsourceProject:add")
    @PostMapping(value = "add")
    public R add(OutsourceProject outsourceProject) {
        outsourceProjectService.save(outsourceProject);
        return R.ok();
    }

    @RequiresPermissions("zg:outsourceProject:edit")
    @PostMapping(value = "edit")
    public R edit(OutsourceProject outsourceProject) {
        outsourceProjectService.updateById(outsourceProject);
        return R.ok();
    }

    @RequiresPermissions("zg:outsourceProject:del")
    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        outsourceProjectService.removeById(id);
        return R.ok();
    }

    @GetMapping(value = "get/{id}")
    public R get(@PathVariable Long id) {
        return R.ok(outsourceProjectService.getById(id));
    }
    @GetMapping(value="showJob")
    public ModelAndView showJob(Long proId){
        ModelAndView mv = new ModelAndView("zg/outsourceJob");
        mv.addObject("proId",proId);
        return mv;
    }
}

