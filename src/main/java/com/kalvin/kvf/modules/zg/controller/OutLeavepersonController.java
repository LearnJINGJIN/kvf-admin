package com.kalvin.kvf.modules.zg.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.common.utils.ShiroKit;
import com.kalvin.kvf.common.utils.SnowflakeIdWorker;
import com.kalvin.kvf.modules.zg.entity.OutLeaveperson;
import com.kalvin.kvf.modules.zg.service.OutLeavepersonService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


/**
 * <p>
 * 外包人员离场登记表 前端控制器
 * </p>
 * @since 2021-03-16 11:21:39
 */
@RestController
@RequestMapping("zg/outLeaveperson")
public class OutLeavepersonController extends BaseController {

    @Autowired
    private OutLeavepersonService outLeavepersonService;

    @RequiresPermissions("zg:outLeaveperson:index")
    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("zg/outLeaveperson");
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id,String deploymentId) {
        ModelAndView mv = new ModelAndView("zg/outLeaveperson_edit");
        OutLeaveperson outLeaveperson;
        if (id == null) {
            outLeaveperson = new OutLeaveperson();
            outLeaveperson.setDeploymentId(deploymentId);
            outLeaveperson.setRegisterCode(SnowflakeIdWorker.generateId().toString());
        } else {
            outLeaveperson = outLeavepersonService.getLeavePersonById(id);
        }
        mv.addObject("editInfo", outLeaveperson);
        return mv;
    }
    @RequiresPermissions("zg:outApplyleave:add")
    @PostMapping(value = "add")
    public R add(OutLeaveperson outLeaveperson) {
        return outLeavepersonService.startForm(outLeaveperson);
    }
    @GetMapping(value = "list/data")
    public R listData(OutLeaveperson outLeaveperson) {
        Page<OutLeaveperson> page = outLeavepersonService.listOutLeavepersonPage(outLeaveperson);
        return R.ok(page);
    }

    @PostMapping(value = "edit")
    public R edit(OutLeaveperson outLeaveperson) {
        outLeaveperson.setUpdateUser(ShiroKit.getUserId());
        outLeavepersonService.updateById(outLeaveperson);
        return R.ok();
    }

    @RequiresPermissions("zg:outLeaveperson:del")
    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        outLeavepersonService.removeById(id);
        return R.ok();
    }

    @GetMapping(value = "get/{id}")
    public R get(@PathVariable Long id) {
        return R.ok(outLeavepersonService.getById(id));
    }

}

