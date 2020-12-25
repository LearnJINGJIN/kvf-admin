package com.kalvin.kvf.modules.zg.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.modules.zg.entity.SystemOperation;
import com.kalvin.kvf.modules.zg.service.SystemOperationService;

import java.util.List;


/**
 * <p>
 * 系统操作管理 前端控制器
 * </p>
 * @since 2020-12-25 09:50:57
 */
@RestController
@RequestMapping("zg/systemOperation")
public class SystemOperationController extends BaseController {

    @Autowired
    private SystemOperationService systemOperationService;

    @RequiresPermissions("zg:systemOperation:index")
    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("zg/systemOperation");
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("zg/systemOperation_edit");
        SystemOperation systemOperation;
        if (id == null) {
            systemOperation = new SystemOperation();
        } else {
            systemOperation = systemOperationService.getInfoById(id);
        }
        mv.addObject("editInfo", systemOperation);
        return mv;
    }

    @PostMapping(value = "list/data")
    public R listData(SystemOperation systemOperation) {
        Page<SystemOperation> page = systemOperationService.listSystemOperationPage(systemOperation);
        return R.ok(page);
    }

    @RequiresPermissions("zg:systemOperation:add")
    @PostMapping(value = "add")
    public R add(SystemOperation systemOperation) {
        systemOperationService.save(systemOperation);
        return R.ok();
    }

    @RequiresPermissions("zg:systemOperation:edit")
    @PostMapping(value = "edit")
    public R edit(SystemOperation systemOperation) {
        systemOperationService.updateById(systemOperation);
        return R.ok();
    }

    @RequiresPermissions("zg:systemOperation:del")
    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        systemOperationService.removeById(id);
        return R.ok();
    }
}

