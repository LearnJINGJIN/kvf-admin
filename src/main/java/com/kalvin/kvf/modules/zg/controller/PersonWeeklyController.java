package com.kalvin.kvf.modules.zg.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kalvin.kvf.modules.zg.entity.OutPerson;
import com.kalvin.kvf.modules.zg.service.OutPersonService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.modules.zg.entity.PersonWeekly;
import com.kalvin.kvf.modules.zg.service.PersonWeeklyService;

import java.util.List;


/**
 * <p>
 * 驻场外包人员周报管理 前端控制器
 * </p>
 * @since 2021-01-21 17:22:57
 */
@RestController
@RequestMapping("zg/personWeekly")
public class PersonWeeklyController extends BaseController {

    @Autowired
    private PersonWeeklyService personWeeklyService;
    @Autowired
    private OutPersonService outPersonService;
    @GetMapping("index")
    public ModelAndView index(Long personId ,Integer outsourceType) {
        ModelAndView   mv=new ModelAndView();
        if (outsourceType==1){
            //开发周报界面
            mv.setViewName("zg/personWeekly1");
         }else if(outsourceType==2){
            //运维周报界面
            mv.setViewName("zg/personWeekly2");
         }
        OutPerson outPerson = outPersonService.getInfoById(personId);
        mv.addObject("personId",personId);
        mv.addObject("personName",outPerson.getName());
        mv.addObject("outsourceType",outsourceType);
        return mv;
    }

    @GetMapping(value = "editWeekly1")
    public ModelAndView editWeekly1(Long id,Long personId) {
        ModelAndView mv = new ModelAndView("zg/personWeekly_edit1");
        PersonWeekly personWeekly;
        if (id == null) {
            personWeekly = new PersonWeekly();
            personWeekly.setPersonId(personId);
        } else {
            personWeekly = personWeeklyService.getById(id);
        }
        mv.addObject("editInfo", personWeekly);
        return mv;
    }

    @GetMapping(value = "editWeekly2")
    public ModelAndView editWeekly2(Long id,Long personId) {
        ModelAndView mv = new ModelAndView("zg/personWeekly_edit2");
        PersonWeekly personWeekly;
        if (id == null) {
            personWeekly = new PersonWeekly();
            personWeekly.setPersonId(personId);
        } else {
            personWeekly = personWeeklyService.getById(id);
        }
        mv.addObject("editInfo", personWeekly);
        return mv;
    }

    @GetMapping(value = "list/data")
    public R listData(PersonWeekly personWeekly) {
        Page<PersonWeekly> page = personWeeklyService.listPersonWeeklyPage(personWeekly);
        return R.ok(page);
    }

     @PostMapping(value = "add")
    public R add(PersonWeekly personWeekly) {
        personWeeklyService.save(personWeekly);
        return R.ok();
    }

     @PostMapping(value = "edit")
    public R edit(PersonWeekly personWeekly) {
        personWeeklyService.updateById(personWeekly);
        return R.ok();
    }

    @RequiresPermissions("zg:personWeekly:del")
    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        personWeeklyService.removeById(id);
        return R.ok();
    }

    @GetMapping(value = "get/{id}")
    public R get(@PathVariable Long id) {
        return R.ok(personWeeklyService.getById(id));
    }

}

