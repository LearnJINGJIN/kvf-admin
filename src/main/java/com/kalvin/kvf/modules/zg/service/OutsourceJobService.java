package com.kalvin.kvf.modules.zg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kalvin.kvf.modules.zg.entity.OutsourceJob;

/**
 * <p>
 * 项目任务清单 服务类
 * </p>
 * @since 2021-01-14 17:26:45
 */
public interface OutsourceJobService extends IService<OutsourceJob> {

    /**
     * 获取列表。分页
     * @param outsourceJob 查询参数
     * @return page
     */
    Page<OutsourceJob> listOutsourceJobPage(OutsourceJob outsourceJob);
    OutsourceJob getOutsourceJobById(Long id);

}
