package com.kalvin.kvf.modules.zg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kalvin.kvf.modules.zg.entity.SystemApp;

/**
 * <p>
 * 系统应用环境管理 服务类
 * </p>
 * @since 2020-12-22 11:29:38
 */
public interface SystemAppService extends IService<SystemApp> {

    /**
     * 获取列表。分页
     * @param systemApp 查询参数
     * @return page
     */
    Page<SystemApp> listSystemAppPage(SystemApp systemApp);
    SystemApp getInfoById(Long id);
 }
