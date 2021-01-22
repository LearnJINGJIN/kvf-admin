package com.kalvin.kvf.modules.zg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kalvin.kvf.modules.zg.entity.PersonWeekly;

/**
 * <p>
 * 驻场外包人员周报管理 服务类
 * </p>
 * @since 2021-01-21 17:22:57
 */
public interface PersonWeeklyService extends IService<PersonWeekly> {

    /**
     * 获取列表。分页
     * @param personWeekly 查询参数
     * @return page
     */
    Page<PersonWeekly> listPersonWeeklyPage(PersonWeekly personWeekly);

}
