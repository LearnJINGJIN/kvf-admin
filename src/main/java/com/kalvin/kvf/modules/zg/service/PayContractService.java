package com.kalvin.kvf.modules.zg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kalvin.kvf.modules.zg.entity.PayContract;

/**
 * <p>
 * 合同-付款对应表 服务类
 * </p>
 * @since 2021-01-27 14:44:04
 */
public interface PayContractService extends IService<PayContract> {

    /**
     * 获取列表。分页
     * @param payContract 查询参数
     * @return page
     */
    Page<PayContract> listPayContractPage(PayContract payContract);

}
