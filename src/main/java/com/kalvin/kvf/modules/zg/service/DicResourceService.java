package com.kalvin.kvf.modules.zg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kalvin.kvf.modules.zg.entity.DicResource;

/**
 * <p>
 *  服务类
 * </p>
 * @since 2020-12-17 10:26:21
 */
public interface DicResourceService extends IService<DicResource> {

    /**
     * 获取列表。分页
     * @param dicResource 查询参数
     * @return page
     */
    Page<DicResource> listDicResourcePage(DicResource dicResource);

}
