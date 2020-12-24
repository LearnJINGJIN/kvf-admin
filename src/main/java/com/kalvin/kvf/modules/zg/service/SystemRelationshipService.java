package com.kalvin.kvf.modules.zg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kalvin.kvf.modules.zg.entity.SystemRelationship;

/**
 * <p>
 * 系统访问关系 服务类
 * </p>
 * @since 2020-12-23 14:52:34
 */
public interface SystemRelationshipService extends IService<SystemRelationship> {

    /**
     * 获取列表。分页
     * @param systemRelationship 查询参数
     * @return page
     */
    Page<SystemRelationship> listSystemRelationshipPage(SystemRelationship systemRelationship);
    SystemRelationship  getEditById(Long id);
}
