package com.kalvin.kvf.modules.zg.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kalvin.kvf.common.utils.ShiroKit;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.modules.zg.entity.OutCompany;
import com.kalvin.kvf.modules.zg.service.OutCompanyService;

import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 * @since 2020-12-17 11:26:13
 */
@RestController
@RequestMapping("zg/outCompany")
public class OutCompanyController extends BaseController {

    @Autowired
    private OutCompanyService outCompanyService;

    @RequiresPermissions("zg:outCompany:index")
    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("zg/outCompany");
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("zg/outCompany_edit");
        OutCompany outCompany;
        if (id == null) {
            outCompany = new OutCompany();
        } else {
            outCompany = outCompanyService.getById(id);
        }
        mv.addObject("editInfo", outCompany);
        return mv;
    }

    @GetMapping(value = "list/data")
    public R listData(OutCompany outCompany) {
        Page<OutCompany> page = outCompanyService.listOutCompanyPage(outCompany);
        return R.ok(page);
    }

    @RequiresPermissions("zg:outCompany:add")
    @PostMapping(value = "add")
    public R add(OutCompany outCompany) {
        outCompany.setCreateId(ShiroKit.getUserId());
        outCompanyService.save(outCompany);
        return R.ok();
    }

    @RequiresPermissions("zg:outCompany:edit")
    @PostMapping(value = "edit")
    public R edit(OutCompany outCompany) {
        outCompany.setUpdateId(ShiroKit.getUserId());
        outCompanyService.updateById(outCompany);
        return R.ok();
    }

    @RequiresPermissions("zg:outCompany:del")
    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        outCompanyService.removeById(id);
        return R.ok();
    }

    @GetMapping(value = "get/{id}")
    public R get(@PathVariable Long id) {
        return R.ok(outCompanyService.getById(id));
    }

}

