package com.kalvin.kvf.modules.zg.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.modules.zg.entity.DicResource;
import com.kalvin.kvf.modules.zg.service.DicResourceService;

import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 * @since 2020-12-17 10:26:21
 */
@RestController
@RequestMapping("zg/dicResource")
public class DicResourceController extends BaseController {

    @Autowired
    private DicResourceService dicResourceService;

    @RequiresPermissions("zg:dicResource:index")
    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("zg/dicResource");
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("zg/dicResource_edit");
        DicResource dicResource;
        if (id == null) {
            dicResource = new DicResource();
        } else {
            dicResource = dicResourceService.getById(id);
        }
        mv.addObject("editInfo", dicResource);
        return mv;
    }

    @GetMapping(value = "list/data")
    public R listData(DicResource dicResource) {
        Page<DicResource> page = dicResourceService.listDicResourcePage(dicResource);
        return R.ok(page);
    }

    @RequiresPermissions("zg:dicResource:add")
    @PostMapping(value = "add")
    public R add(DicResource dicResource) {
        dicResourceService.save(dicResource);
        return R.ok();
    }

    @RequiresPermissions("zg:dicResource:edit")
    @PostMapping(value = "edit")
    public R edit(DicResource dicResource) {
        dicResourceService.updateById(dicResource);
        return R.ok();
    }

    @RequiresPermissions("zg:dicResource:del")
    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        dicResourceService.removeById(id);
        return R.ok();
    }

    @GetMapping(value = "get/{id}")
    public R get(@PathVariable Long id) {
        return R.ok(dicResourceService.getById(id));
    }

}

