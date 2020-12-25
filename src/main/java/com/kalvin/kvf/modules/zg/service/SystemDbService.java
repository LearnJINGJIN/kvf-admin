package com.kalvin.kvf.modules.zg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kalvin.kvf.modules.zg.entity.SystemDb;

/**
 * <p>
 * 系统数据库环境管理 服务类
 * </p>
 * @since 2020-12-24 16:56:21
 */
public interface SystemDbService extends IService<SystemDb> {

    /**
     * 获取列表。分页
     * @param systemDb 查询参数
     * @return page
     */
    Page<SystemDb> listSystemDbPage(SystemDb systemDb);
    SystemDb getInfoById(Long id);

}
