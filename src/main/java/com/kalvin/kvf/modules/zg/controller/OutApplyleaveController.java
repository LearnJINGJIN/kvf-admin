package com.kalvin.kvf.modules.zg.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.modules.zg.entity.OutApplyleave;
import com.kalvin.kvf.modules.zg.service.OutApplyleaveService;

import java.util.List;


/**
 * <p>
 * 外包请假管理 前端控制器
 * </p>
 * @since 2021-03-03 15:05:11
 */
@RestController
@RequestMapping("zg/outApplyleave")
public class OutApplyleaveController extends BaseController {

    @Autowired
    private OutApplyleaveService outApplyleaveService;

    @RequiresPermissions("zg:outApplyleave:index")
    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("zg/outApplyleave");
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("zg/outApplyleave_edit");
        OutApplyleave outApplyleave;
        if (id == null) {
            outApplyleave = new OutApplyleave();
        } else {
            outApplyleave = outApplyleaveService.getOutApplyleaveById(id);
        }
        mv.addObject("editInfo", outApplyleave);
        return mv;
    }

    @GetMapping(value = "list/data")
    public R listData(OutApplyleave outApplyleave) {
        Page<OutApplyleave> page = outApplyleaveService.listOutApplyleavePage(outApplyleave);
        return R.ok(page);
    }

    @RequiresPermissions("zg:outApplyleave:add")
    @PostMapping(value = "add")
    public R add(OutApplyleave outApplyleave) {
        outApplyleaveService.save(outApplyleave);
        return R.ok();
    }

    @RequiresPermissions("zg:outApplyleave:edit")
    @PostMapping(value = "edit")
    public R edit(OutApplyleave outApplyleave) {
        outApplyleaveService.updateById(outApplyleave);
        return R.ok();
    }

    @RequiresPermissions("zg:outApplyleave:del")
    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        outApplyleaveService.removeById(id);
        return R.ok();
    }

    @GetMapping(value = "get/{id}")
    public R get(@PathVariable Long id) {
        return R.ok(outApplyleaveService.getById(id));
    }

}

