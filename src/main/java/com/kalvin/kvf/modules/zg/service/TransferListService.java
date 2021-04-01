package com.kalvin.kvf.modules.zg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kalvin.kvf.modules.zg.entity.TransferList;

/**
 * <p>
 * 交接清单表 服务类
 * </p>
 * @since 2021-03-16 11:41:47
 */
public interface TransferListService extends IService<TransferList> {

    /**
     * 获取列表。分页
     * @param transferList 查询参数
     * @return page
     */
    Page<TransferList> listTransferListPage(TransferList transferList);

}
