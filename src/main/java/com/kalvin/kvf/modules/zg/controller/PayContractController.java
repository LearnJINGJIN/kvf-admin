package com.kalvin.kvf.modules.zg.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kalvin.kvf.common.utils.SnowflakeIdWorker;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.modules.zg.entity.PayContract;
import com.kalvin.kvf.modules.zg.service.PayContractService;

import java.util.List;


/**
 * <p>
 * 合同-付款对应表 前端控制器
 * </p>
 * @since 2021-01-27 14:44:04
 */
@RestController
@RequestMapping("zg/payContract")
public class PayContractController extends BaseController {

    @Autowired
    private PayContractService payContractService;


    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("zg/payContract");
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id,String contractNo) {
        ModelAndView mv = new ModelAndView("zg/payContract_edit");
        PayContract payContract;
        if (id == null) {
            payContract = new PayContract();
            payContract.setContractNo(contractNo);
            payContract.setPayNo("pay"+ SnowflakeIdWorker.generateId());
        } else {
            payContract = payContractService.getById(id);
        }
        mv.addObject("editInfo", payContract);
        return mv;
    }

    @GetMapping(value = "list/data")
    public R listData(PayContract payContract) {
        Page<PayContract> page = payContractService.listPayContractPage(payContract);
        return R.ok(page);
    }

    @PostMapping(value = "add")
    public R add(PayContract payContract) {
        payContractService.save(payContract);
        return R.ok();
    }

     @PostMapping(value = "edit")
    public R edit(PayContract payContract) {
        payContractService.updateById(payContract);
        return R.ok();
    }

    @RequiresPermissions("zg:payContract:del")
    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        payContractService.removeById(id);
        return R.ok();
    }

    @GetMapping(value = "get/{id}")
    public R get(@PathVariable Long id) {
        return R.ok(payContractService.getById(id));
    }

}

