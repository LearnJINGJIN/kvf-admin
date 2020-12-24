package com.kalvin.kvf.modules.zg.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.modules.zg.entity.SystemRelationship;
import com.kalvin.kvf.modules.zg.service.SystemRelationshipService;

import java.util.List;


/**
 * <p>
 * 系统访问关系 前端控制器
 * </p>
 * @since 2020-12-23 14:52:34
 */
@RestController
@RequestMapping("zg/systemRelationship")
public class SystemRelationshipController extends BaseController {

    @Autowired
    private SystemRelationshipService systemRelationshipService;

    @RequiresPermissions("zg:systemRelationship:index")
    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("zg/systemRelationship");
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("zg/systemRelationship_edit");
        SystemRelationship systemRelationship;
        if (id == null) {
            systemRelationship = new SystemRelationship();
        } else {
            systemRelationship = systemRelationshipService.getEditById(id);
        }
        mv.addObject("editInfo", systemRelationship);
        return mv;
    }

    @PostMapping(value = "list/data")
    public R listData(SystemRelationship systemRelationship) {
        Page<SystemRelationship> page = systemRelationshipService.listSystemRelationshipPage(systemRelationship);
        return R.ok(page);
    }

    @RequiresPermissions("zg:systemRelationship:add")
    @PostMapping(value = "add")
    public R add(SystemRelationship systemRelationship) {
        systemRelationshipService.save(systemRelationship);
        return R.ok();
    }

    @RequiresPermissions("zg:systemRelationship:edit")
    @PostMapping(value = "edit")
    public R edit(SystemRelationship systemRelationship) {
        systemRelationshipService.updateById(systemRelationship);
        return R.ok();
    }

    @RequiresPermissions("zg:systemRelationship:del")
    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        systemRelationshipService.removeById(id);
        return R.ok();
    }

    @GetMapping(value = "get/{id}")
    public R get(@PathVariable Long id) {
        return R.ok(systemRelationshipService.getById(id));
    }
    //动态修改
    @RequiresPermissions("zg:systemRelationship:changeUse")
    @PostMapping(value = "changeUse")
    public R changeUse(SystemRelationship systemRelationship) {
        if(systemRelationship.getId()==null){
            return R.fail("数据未选中!");
        }
        if("on".equals(systemRelationship.getStatus())){
            systemRelationship.setIsUse(0);
        }else {
            systemRelationship.setIsUse(1);
        }
        systemRelationship.setStatus(null);
        return R.ok(systemRelationshipService.updateById(systemRelationship));
    }

}

