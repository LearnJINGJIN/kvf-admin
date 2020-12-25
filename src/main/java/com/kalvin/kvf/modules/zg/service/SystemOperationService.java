package com.kalvin.kvf.modules.zg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kalvin.kvf.modules.zg.entity.SystemOperation;

/**
 * <p>
 * 系统操作管理 服务类
 * </p>
 * @since 2020-12-25 09:50:57
 */
public interface SystemOperationService extends IService<SystemOperation> {

    /**
     * 获取列表。分页
     * @param systemOperation 查询参数
     * @return page
     */
    Page<SystemOperation> listSystemOperationPage(SystemOperation systemOperation);
    SystemOperation getInfoById(Long id);
}
