package com.kalvin.kvf.modules.zg.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kalvin.kvf.common.utils.ShiroKit;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.modules.zg.entity.System;
import com.kalvin.kvf.modules.zg.service.SystemService;


/**
 * <p>
 * 系统基本表 前端控制器
 * </p>
 * @since 2020-12-15 17:20:28
 * jingjin
 */
@RestController
@RequestMapping("zg/system")
public class SystemController extends BaseController {

    @Autowired
    private SystemService systemService;

    @RequiresPermissions("zg:system:index")
    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("zg/system");
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("zg/system_edit");
        System system;
        if (id == null) {
            system = new System();
        } else {
            system = systemService.getInfoById(id);
        }
        mv.addObject("editInfo", system);
        return mv;
    }

    @PostMapping(value = "list/data")
    public R listData(System system) {
        Page<System> page = systemService.listSystemPage(system);
        return R.ok(page);
    }

    @RequiresPermissions("zg:system:add")
    @PostMapping(value = "add")
    public R add(System system) {
        system.setCreateUser(ShiroKit.getUserId());
        systemService.save(system);
        return R.ok();
    }

    @RequiresPermissions("zg:system:edit")
    @PostMapping(value = "edit")
    public R edit(System system) {
        system.setUpdateUser(ShiroKit.getUserId());
        systemService.updateById(system);
        return R.ok();
    }

    @RequiresPermissions("zg:system:del")
    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        systemService.removeById(id);
        return R.ok();
    }



}

