package com.kalvin.kvf.modules.zg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kalvin.kvf.modules.zg.entity.OutsourceContract;

/**
 * <p>
 * 合同管理 服务类
 * </p>
 * @since 2021-01-26 10:36:16
 */
public interface OutsourceContractService extends IService<OutsourceContract> {

    /**
     * 获取列表。分页
     * @param outsourceContract 查询参数
     * @return page
     */
    Page<OutsourceContract> listOutsourceContractPage(OutsourceContract outsourceContract);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    OutsourceContract getOutsourceContractByid(Long id);

}
