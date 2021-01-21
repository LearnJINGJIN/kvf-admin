package com.kalvin.kvf.modules.zg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kalvin.kvf.modules.zg.entity.OutPlan;

import java.util.List;

/**
 * <p>
 * 年度外包计划表 Mapper 接口
 * </p>
 * @since 2021-01-05 10:54:44
 */
public interface OutPlanMapper extends BaseMapper<OutPlan> {

    /**
     * 查询列表(分页)
     * @param outPlan 查询参数
     * @param page 分页参数
     * @return list
     */
    List<OutPlan> selectOutPlanList(OutPlan outPlan, IPage page);

}
