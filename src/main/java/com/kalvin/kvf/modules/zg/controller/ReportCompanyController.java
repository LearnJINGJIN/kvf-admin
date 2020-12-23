package com.kalvin.kvf.modules.zg.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.modules.zg.entity.ReportCompany;
import com.kalvin.kvf.modules.zg.service.ReportCompanyService;

import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 * @since 2020-12-17 16:31:10
 */
@RestController
@RequestMapping("zg/reportCompany")
public class ReportCompanyController extends BaseController {

    @Autowired
    private ReportCompanyService reportCompanyService;

    @RequiresPermissions("zg:reportCompany:index")
    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("zg/reportCompany");
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("zg/reportCompany_edit");
        ReportCompany reportCompany;
        if (id == null) {
            reportCompany = new ReportCompany();
        } else {
            reportCompany = reportCompanyService.getById(id);
        }
        mv.addObject("editInfo", reportCompany);
        return mv;
    }

    @GetMapping(value = "list/data")
    public R listData(ReportCompany reportCompany) {
        Page<ReportCompany> page = reportCompanyService.listReportCompanyPage(reportCompany);
        return R.ok(page);
    }

    @RequiresPermissions("zg:reportCompany:add")
    @PostMapping(value = "add")
    public R add(ReportCompany reportCompany) {
        reportCompanyService.save(reportCompany);
        return R.ok();
    }

    @RequiresPermissions("zg:reportCompany:edit")
    @PostMapping(value = "edit")
    public R edit(ReportCompany reportCompany) {
        reportCompanyService.updateById(reportCompany);
        return R.ok();
    }

    @RequiresPermissions("zg:reportCompany:del")
    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        reportCompanyService.removeById(id);
        return R.ok();
    }

    @GetMapping(value = "get/{id}")
    public R get(@PathVariable Long id) {
        return R.ok(reportCompanyService.getById(id));
    }

}

