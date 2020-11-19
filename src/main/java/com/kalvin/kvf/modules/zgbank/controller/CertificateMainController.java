package com.kalvin.kvf.modules.zgbank.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.modules.zgbank.entity.CertificateMain;
import com.kalvin.kvf.modules.zgbank.service.CertificateMainService;

import java.util.List;


/**
 * <p>
 * 证书管理表 前端控制器
 * </p>
 * @since 2020-09-28 16:23:00
 */
@RestController
@RequestMapping("zgbank/certificateMain")
public class CertificateMainController extends BaseController {

    @Autowired
    private CertificateMainService certificateMainService;

    @RequiresPermissions("zgbank:certificateMain:index")
    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("zgbank/certificateMain");
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("zgbank/certificateMain_edit");
        CertificateMain certificateMain;
        if (id == null) {
            certificateMain = new CertificateMain();
        } else {
            certificateMain = certificateMainService.getById(id);
        }
        mv.addObject("editInfo", certificateMain);
        return mv;
    }

    @GetMapping(value = "list/data")
    public R listData(CertificateMain certificateMain) {
        Page<CertificateMain> page = certificateMainService.listCertificateMainPage(certificateMain);
        return R.ok(page);
    }

    @RequiresPermissions("zgbank:certificateMain:add")
    @PostMapping(value = "add")
    public R add(CertificateMain certificateMain) {
        certificateMainService.save(certificateMain);
        return R.ok();
    }

    @RequiresPermissions("zgbank:certificateMain:del")
    @PostMapping(value = "batchdel")
    public R batchdel(@RequestParam("ids") List<Long> ids) {
        certificateMainService.removeByIds(ids);
        return R.ok();
    }

    @RequiresPermissions("zgbank:certificateMain:edit")
    @PostMapping(value = "edit")
    public R edit(CertificateMain certificateMain) {
        certificateMainService.updateById(certificateMain);
        return R.ok();
    }

    @RequiresPermissions("zgbank:certificateMain:del")
    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        certificateMainService.removeById(id);
        return R.ok();
    }

    @GetMapping(value = "get/{id}")
    public R get(@PathVariable Long id) {
        return R.ok(certificateMainService.getById(id));
    }

}

