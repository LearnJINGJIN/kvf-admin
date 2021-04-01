package com.kalvin.kvf.modules.zg.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kalvin.kvf.common.utils.ShiroKit;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.modules.zg.entity.OutPerson;
import com.kalvin.kvf.modules.zg.service.OutPersonService;

import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 * @since 2020-12-17 11:48:19
 */
@RestController
@RequestMapping("zg/outPerson")
public class OutPersonController extends BaseController {

    @Autowired
    private OutPersonService outPersonService;

    @RequiresPermissions("zg:outPerson:index")
    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("zg/outPerson");
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("zg/outPerson_edit");
        OutPerson outPerson;
        if (id == null) {
            outPerson = new OutPerson();
        } else {
            outPerson = outPersonService.getInfoById(id);
        }
        mv.addObject("editInfo", outPerson);
        return mv;
    }

    @GetMapping(value = "list/data")
    public R listData(OutPerson outPerson) {
        Page<OutPerson> page = outPersonService.listOutPersonPage(outPerson);
        return R.ok(page);
    }


    @RequiresPermissions("zg:outPerson:add")
    @PostMapping(value = "add")
    public R add(OutPerson outPerson) {
        outPerson.setCreateUser(ShiroKit.getUserId());
        outPersonService.save(outPerson);
        return R.ok();
    }

    @RequiresPermissions("zg:outPerson:edit")
    @PostMapping(value = "edit")
    public R edit(OutPerson outPerson) {
        outPerson.setUpdateUser(ShiroKit.getUserId());
        outPersonService.updateById(outPerson);
        return R.ok();
    }

    @RequiresPermissions("zg:outPerson:del")
    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        outPersonService.removeById(id);
        return R.ok();
    }

    @GetMapping(value = "get/{id}")
    public R get(@PathVariable Long id) {
        return R.ok(outPersonService.getById(id));
    }

}

