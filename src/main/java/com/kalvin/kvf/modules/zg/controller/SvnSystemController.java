package com.kalvin.kvf.modules.zg.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kalvin.kvf.common.utils.ShiroKit;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.modules.zg.entity.SvnSystem;
import com.kalvin.kvf.modules.zg.service.SvnSystemService;

import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 * @since 2020-12-15 14:48:46
 * jingjin
 */
@RestController
@RequestMapping("zg/svnSystem")
public class SvnSystemController extends BaseController {

    @Autowired
    private SvnSystemService svnSystemService;

    @RequiresPermissions("zg:svnSystem:index")
    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("zg/svnSystem");
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("zg/svnSystem_edit");
        SvnSystem svnSystem;
        if (id == null) {
            svnSystem = new SvnSystem();
        } else {
            svnSystem = svnSystemService.getInfoById(id);
        }
        mv.addObject("editInfo", svnSystem);
        return mv;
    }

    @GetMapping(value = "list/data")
    public R listData(SvnSystem svnSystem) {
        Page<SvnSystem> page = svnSystemService.listSvnSystemPage(svnSystem);
        return R.ok(page);
    }

    @RequiresPermissions("zg:svnSystem:add")
    @PostMapping(value = "add")
    public R add(SvnSystem svnSystem) {
        svnSystem.setCreateUser(ShiroKit.getUserId());
        svnSystemService.save(svnSystem);
        return R.ok();
    }

    @RequiresPermissions("zg:svnSystem:edit")
    @PostMapping(value = "edit")
    public R edit(SvnSystem svnSystem) {
        svnSystem.setUpdateUser(ShiroKit.getUserId());
        svnSystemService.updateById(svnSystem);
        return R.ok();
    }

    @RequiresPermissions("zg:svnSystem:del")
    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        svnSystemService.removeById(id);
        return R.ok();
    }

    @GetMapping(value = "get/{id}")
    public R get(@PathVariable Long id) {
        return R.ok(svnSystemService.getById(id));
    }

}

