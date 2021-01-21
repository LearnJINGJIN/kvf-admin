package com.kalvin.kvf.modules.zg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kalvin.kvf.modules.zg.entity.OutPlan;

/**
 * <p>
 * 年度外包计划表 服务类
 * </p>
 * @since 2021-01-05 10:54:44
 */
public interface OutPlanService extends IService<OutPlan> {

    /**
     * 获取列表。分页
     * @param outPlan 查询参数
     * @return page
     */
    Page<OutPlan> listOutPlanPage(OutPlan outPlan);

}
