package com.kalvin.kvf.modules.zg.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.modules.zg.entity.Project;
import com.kalvin.kvf.modules.zg.service.ProjectService;

import java.util.List;


/**
 * <p>
 * 项目/需求管理 前端控制器
 * </p>
 * @since 2020-12-29 11:22:59
 */
@RestController
@RequestMapping("zg/project")
public class ProjectController extends BaseController {

    @Autowired
    private ProjectService projectService;

    @RequiresPermissions("zg:project:index")
    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("zg/project");
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("zg/project_edit");
        Project project;
        if (id == null) {
            project = new Project();
        } else {
            project = projectService.getById(id);
        }
        mv.addObject("editInfo", project);
        return mv;
    }

    @GetMapping(value = "list/data")
    public R listData(Project project) {
        Page<Project> page = projectService.listProjectPage(project);
        return R.ok(page);
    }

    @RequiresPermissions("zg:project:add")
    @PostMapping(value = "add")
    public R add(Project project) {
        projectService.save(project);
        return R.ok();
    }

    @RequiresPermissions("zg:project:edit")
    @PostMapping(value = "edit")
    public R edit(Project project) {
        projectService.updateById(project);
        return R.ok();
    }

    @RequiresPermissions("zg:project:del")
    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        projectService.removeById(id);
        return R.ok();
    }

    @GetMapping(value = "get/{id}")
    public R get(@PathVariable Long id) {
        return R.ok(projectService.getById(id));
    }

}

