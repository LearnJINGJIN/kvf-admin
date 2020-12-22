package com.kalvin.kvf.modules.zg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kalvin.kvf.modules.zg.entity.System;

/**
 * <p>
 * 系统基本表 服务类
 * </p>
 * @since 2020-12-15 17:20:28
 */
public interface SystemService extends IService<System> {

    /**
     * 获取列表。分页
     * @param system 查询参数
     * @return page
     */
    Page<System> listSystemPage(System system);

    System getInfoById(Long id);

}
