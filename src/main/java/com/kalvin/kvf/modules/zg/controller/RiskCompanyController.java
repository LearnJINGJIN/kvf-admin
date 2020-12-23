package com.kalvin.kvf.modules.zg.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.modules.zg.entity.RiskCompany;
import com.kalvin.kvf.modules.zg.service.RiskCompanyService;

import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 * @since 2020-12-17 11:53:25
 */
@RestController
@RequestMapping("zg/riskCompany")
public class RiskCompanyController extends BaseController {

    @Autowired
    private RiskCompanyService riskCompanyService;

    @RequiresPermissions("zg:riskCompany:index")
    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("zg/riskCompany");
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("zg/riskCompany_edit");
        RiskCompany riskCompany;
        if (id == null) {
            riskCompany = new RiskCompany();
        } else {
            riskCompany = riskCompanyService.getById(id);
        }
        mv.addObject("editInfo", riskCompany);
        return mv;
    }

    @GetMapping(value = "list/data")
    public R listData(RiskCompany riskCompany) {
        Page<RiskCompany> page = riskCompanyService.listRiskCompanyPage(riskCompany);
        return R.ok(page);
    }

    @RequiresPermissions("zg:riskCompany:add")
    @PostMapping(value = "add")
    public R add(RiskCompany riskCompany) {
        riskCompanyService.save(riskCompany);
        return R.ok();
    }

    @RequiresPermissions("zg:riskCompany:edit")
    @PostMapping(value = "edit")
    public R edit(RiskCompany riskCompany) {
        riskCompanyService.updateById(riskCompany);
        return R.ok();
    }

    @RequiresPermissions("zg:riskCompany:del")
    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        riskCompanyService.removeById(id);
        return R.ok();
    }

    @GetMapping(value = "get/{id}")
    public R get(@PathVariable Long id) {
        return R.ok(riskCompanyService.getById(id));
    }

}

