package com.kalvin.kvf.modules.zg.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kalvin.kvf.common.utils.ShiroKit;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.modules.zg.entity.DocTemplate;
import com.kalvin.kvf.modules.zg.service.DocTemplateService;

import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 * @since 2020-12-16 17:34:10
 */
@RestController
@RequestMapping("zg/docTemplate")
public class DocTemplateController extends BaseController {

    @Autowired
    private DocTemplateService docTemplateService;

    @RequiresPermissions("zg:docTemplate:index")
    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("zg/docTemplate");
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("zg/docTemplate_edit");
        DocTemplate docTemplate;
        if (id == null) {
            docTemplate = new DocTemplate();
        } else {
            docTemplate = docTemplateService.getById(id);
        }
        mv.addObject("editInfo", docTemplate);
        return mv;
    }

    @GetMapping(value = "list/data")
    public R listData(DocTemplate docTemplate) {
        Page<DocTemplate> page = docTemplateService.listDocTemplatePage(docTemplate);
        return R.ok(page);
    }

    @RequiresPermissions("zg:docTemplate:add")
    @PostMapping(value = "add")
    public R add(DocTemplate docTemplate) {
        docTemplate.setCreateUser(ShiroKit.getUserId());
        docTemplateService.save(docTemplate);
        return R.ok();
    }

    @RequiresPermissions("zg:docTemplate:edit")
    @PostMapping(value = "edit")
    public R edit(DocTemplate docTemplate) {
        docTemplate.setUpdateUser(ShiroKit.getUserId());
        docTemplateService.updateById(docTemplate);
        return R.ok();
    }

    @RequiresPermissions("zg:docTemplate:del")
    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        docTemplateService.removeById(id);
        return R.ok();
    }

    @GetMapping(value = "get/{id}")
    public R get(@PathVariable Long id) {
        return R.ok(docTemplateService.getById(id));
    }

}

