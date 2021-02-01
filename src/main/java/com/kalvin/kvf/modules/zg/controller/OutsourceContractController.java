package com.kalvin.kvf.modules.zg.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kalvin.kvf.common.utils.DateUtils;
import com.kalvin.kvf.common.utils.SnowflakeIdWorker;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.modules.zg.entity.OutsourceContract;
import com.kalvin.kvf.modules.zg.service.OutsourceContractService;

import java.util.List;


/**
 * <p>
 * 合同管理 前端控制器
 * </p>
 * @since 2021-01-26 10:36:16
 */
@RestController
@RequestMapping("zg/outsourceContract")
public class OutsourceContractController extends BaseController {

    @Autowired
    private OutsourceContractService outsourceContractService;

    @RequiresPermissions("zg:outsourceContract:index")
    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("zg/outsourceContract");
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("zg/outsourceContract_edit");
        OutsourceContract outsourceContract;
        if (id == null) {
            outsourceContract = new OutsourceContract();
            outsourceContract.setContractNo("contract"+ SnowflakeIdWorker.generateId());
        } else {
            outsourceContract = outsourceContractService.getOutsourceContractByid(id);
        }
        mv.addObject("editInfo", outsourceContract);
        return mv;
    }

    @GetMapping(value = "list/data")
    public R listData(OutsourceContract outsourceContract) {
        Page<OutsourceContract> page = outsourceContractService.listOutsourceContractPage(outsourceContract);
        return R.ok(page);
    }

    @RequiresPermissions("zg:outsourceContract:add")
    @PostMapping(value = "add")
    public R add(OutsourceContract outsourceContract) {
        outsourceContractService.save(outsourceContract);
        return R.ok();
    }

    @RequiresPermissions("zg:outsourceContract:del")
    @PostMapping(value = "batchdel")
    public R batchdel(@RequestParam("ids") List<Long> ids) {
        outsourceContractService.removeByIds(ids);
        return R.ok();
    }

    @RequiresPermissions("zg:outsourceContract:edit")
    @PostMapping(value = "edit")
    public R edit(OutsourceContract outsourceContract) {
        outsourceContractService.updateById(outsourceContract);
        return R.ok();
    }

    @RequiresPermissions("zg:outsourceContract:del")
    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        outsourceContractService.removeById(id);
        return R.ok();
    }

    @GetMapping(value = "get/{id}")
    public R get(@PathVariable Long id) {
        return R.ok(outsourceContractService.getById(id));
    }

}

