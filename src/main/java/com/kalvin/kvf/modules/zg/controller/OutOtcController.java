package com.kalvin.kvf.modules.zg.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kalvin.kvf.common.utils.ShiroKit;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.modules.zg.entity.OutOtc;
import com.kalvin.kvf.modules.zg.service.OutOtcService;

import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 * @since 2021-01-14 15:13:47
 */
@RestController
@RequestMapping("zg/outOtc")
public class OutOtcController extends BaseController {

    @Autowired
    private OutOtcService outOtcService;

    @RequiresPermissions("zg:outOtc:index")
    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("zg/outOtc");
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("zg/outOtc_edit");
        OutOtc outOtc;
        if (id == null) {
            outOtc = new OutOtc();
        } else {
            outOtc = outOtcService.getById(id);
        }
        mv.addObject("editInfo", outOtc);
        return mv;
    }

    @GetMapping(value = "list/data")
    public R listData(OutOtc outOtc) {
        Page<OutOtc> page = outOtcService.listOutOtcPage(outOtc);
        return R.ok(page);
    }

    @RequiresPermissions("zg:outOtc:add")
    @PostMapping(value = "add")
    public R add(OutOtc outOtc) {
        outOtc.setCreateUser(ShiroKit.getUserId());
        outOtcService.save(outOtc);
        return R.ok();
    }

    @RequiresPermissions("zg:outOtc:edit")
    @PostMapping(value = "edit")
    public R edit(OutOtc outOtc) {
        outOtc.setUpdateUser(ShiroKit.getUserId());
        outOtcService.updateById(outOtc);
        return R.ok();
    }

    @RequiresPermissions("zg:outOtc:del")
    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        outOtcService.removeById(id);
        return R.ok();
    }

    @GetMapping(value = "get/{id}")
    public R get(@PathVariable Long id) {
        return R.ok(outOtcService.getById(id));
    }

}

