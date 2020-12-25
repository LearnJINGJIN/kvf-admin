package com.kalvin.kvf.modules.zg.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.modules.zg.entity.SystemDb;
import com.kalvin.kvf.modules.zg.service.SystemDbService;

import java.util.List;


/**
 * <p>
 * 系统数据库环境管理 前端控制器
 * </p>
 * @since 2020-12-24 16:56:21
 */
@RestController
@RequestMapping("zg/systemDb")
public class SystemDbController extends BaseController {

    @Autowired
    private SystemDbService systemDbService;

    @RequiresPermissions("zg:systemDb:index")
    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("zg/systemDb");
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("zg/systemDb_edit");
        SystemDb systemDb;
        if (id == null) {
            systemDb = new SystemDb();
        } else {
            systemDb = systemDbService.getInfoById(id);
        }
        mv.addObject("editInfo", systemDb);
        return mv;
    }

    @PostMapping(value = "list/data")
    public R listData(SystemDb systemDb) {
        Page<SystemDb> page = systemDbService.listSystemDbPage(systemDb);
        return R.ok(page);
    }

    @RequiresPermissions("zg:systemDb:add")
    @PostMapping(value = "add")
    public R add(SystemDb systemDb) {
        systemDbService.save(systemDb);
        return R.ok();
    }

    @RequiresPermissions("zg:systemDb:edit")
    @PostMapping(value = "edit")
    public R edit(SystemDb systemDb) {
        systemDbService.updateById(systemDb);
        return R.ok();
    }

    @RequiresPermissions("zg:systemDb:del")
    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        systemDbService.removeById(id);
        return R.ok();
    }



}

