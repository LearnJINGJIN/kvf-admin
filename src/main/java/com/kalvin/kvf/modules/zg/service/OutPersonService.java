package com.kalvin.kvf.modules.zg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kalvin.kvf.modules.zg.entity.OutPerson;

/**
 * <p>
 *  服务类
 * </p>
 * @since 2020-12-17 11:48:19
 */
public interface OutPersonService extends IService<OutPerson> {

    /**
     * 获取列表。分页
     * @param outPerson 查询参数
     * @return page
     */
    Page<OutPerson> listOutPersonPage(OutPerson outPerson);

}
