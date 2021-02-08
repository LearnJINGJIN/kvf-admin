package com.kalvin.kvf.modules.zg.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.modules.zg.entity.MattersDetail;
import com.kalvin.kvf.modules.zg.service.MattersDetailService;

import java.util.List;


/**
 * <p>
 * 具体事项完成明细表 前端控制器
 * </p>
 * @since 2021-01-05 16:22:27
 */
@RestController
@RequestMapping("zg/mattersDetail")
public class MattersDetailController extends BaseController {

    @Autowired
    private MattersDetailService mattersDetailService;

    @GetMapping("index")
    public ModelAndView index(Long planId) {
        ModelAndView  mv=  new ModelAndView("zg/mattersDetail");
        mv.addObject("planId",planId);
        return mv;
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("zg/mattersDetail_edit");
        MattersDetail mattersDetail;
        if (id == null) {
            mattersDetail = new MattersDetail();
        } else {
            mattersDetail = mattersDetailService.getById(id);
        }
        mv.addObject("editInfo", mattersDetail);
        return mv;
    }

    @GetMapping(value = "list/data")
    public R listData(MattersDetail mattersDetail) {
        Page<MattersDetail> page = mattersDetailService.listMattersDetailPage(mattersDetail);
        return R.ok(page);
    }

     @PostMapping(value = "edit")
    public R edit(MattersDetail mattersDetail) {
        mattersDetailService.updateById(mattersDetail);
        return R.ok();
    }

    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        mattersDetailService.removeById(id);
        return R.ok();
    }

    @GetMapping(value = "get/{id}")
    public R get(@PathVariable Long id) {
        return R.ok(mattersDetailService.getById(id));
    }

}

