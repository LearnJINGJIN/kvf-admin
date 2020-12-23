package com.kalvin.kvf.modules.zg.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kalvin.kvf.common.utils.ShiroKit;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.modules.zg.entity.SystemApp;
import com.kalvin.kvf.modules.zg.service.SystemAppService;

import java.util.List;


/**
 * <p>
 * 系统应用环境管理 前端控制器
 * </p>
 * @since 2020-12-22 11:29:38
 * jingjin
 */
@RestController
@RequestMapping("zg/systemApp")
public class SystemAppController extends BaseController {

    @Autowired
    private SystemAppService systemAppService;

    @RequiresPermissions("zg:systemApp:index")
    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("zg/systemApp");
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("zg/systemApp_edit");
        SystemApp systemApp;
        if (id == null) {
            systemApp = new SystemApp();
        } else {
            systemApp = systemAppService.getInfoById(id);
        }
        mv.addObject("editInfo", systemApp);
        return mv;
    }

    @PostMapping(value = "list/data")
    public R listData(SystemApp systemApp) {
        Page<SystemApp> page = systemAppService.listSystemAppPage(systemApp);
        return R.ok(page);
    }

    @RequiresPermissions("zg:systemApp:add")
    @PostMapping(value = "add")
    public R add(SystemApp systemApp) {
        systemApp.setCreateUser(ShiroKit.getUserId());
        systemAppService.save(systemApp);
        return R.ok();
    }

    @RequiresPermissions("zg:systemApp:edit")
    @PostMapping(value = "edit")
    public R edit(SystemApp systemApp) {
        systemApp.setUpdateUser(ShiroKit.getUserId());
        systemAppService.updateById(systemApp);
        return R.ok();
    }

    @RequiresPermissions("zg:systemApp:del")
    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        systemAppService.removeById(id);
        return R.ok();
    }

    @GetMapping(value = "get/{id}")
    public R get(@PathVariable Long id) {
        return R.ok(systemAppService.getById(id));
    }

}

