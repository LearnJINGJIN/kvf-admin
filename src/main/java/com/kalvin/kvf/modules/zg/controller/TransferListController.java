package com.kalvin.kvf.modules.zg.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.modules.zg.entity.TransferList;
import com.kalvin.kvf.modules.zg.service.TransferListService;

import java.util.List;


/**
 * <p>
 * 交接清单表 前端控制器
 * </p>
 * @since 2021-03-16 11:41:47
 */
@RestController
@RequestMapping("zg/transferList")
public class TransferListController extends BaseController {

    @Autowired
    private TransferListService transferListService;

    @RequiresPermissions("zg:transferList:index")
    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("zg/transferList");
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id,String registerCode) {
        ModelAndView mv = new ModelAndView("zg/transferList_edit");
        TransferList transferList;
        if (id == null) {
            transferList = new TransferList();
            transferList.setRegisterCode(registerCode);
        } else {
            transferList = transferListService.getById(id);
        }
        mv.addObject("editInfo", transferList);
        return mv;
    }

    @GetMapping(value = "list/data")
    public R listData(TransferList transferList) {
        Page<TransferList> page = transferListService.listTransferListPage(transferList);
        return R.ok(page);
    }

     @PostMapping(value = "add")
    public R add(TransferList transferList) {
        transferListService.save(transferList);
        return R.ok();
    }

     @PostMapping(value = "edit")
    public R edit(TransferList transferList) {
        transferListService.updateById(transferList);
        return R.ok();
    }

     @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        transferListService.removeById(id);
        return R.ok();
    }

    @GetMapping(value = "get/{id}")
    public R get(@PathVariable Long id) {
        return R.ok(transferListService.getById(id));
    }

}

