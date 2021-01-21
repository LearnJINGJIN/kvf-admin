package com.kalvin.kvf.modules.zg.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kalvin.kvf.modules.zg.service.OutPlanService;
import org.springframework.stereotype.Service;
import com.kalvin.kvf.modules.zg.entity.OutPlan;
import com.kalvin.kvf.modules.zg.mapper.OutPlanMapper;

import java.util.List;

/**
 * <p>
 * 年度外包计划表 服务实现类
 * </p>
 * @since 2021-01-05 10:54:44
 */
@Service
public class OutPlanServiceImpl extends ServiceImpl<OutPlanMapper, OutPlan> implements OutPlanService {

    @Override
    public Page<OutPlan> listOutPlanPage(OutPlan outPlan) {
        Page<OutPlan> page = new Page<>(outPlan.getCurrent(), outPlan.getSize());
        List<OutPlan> outPlans = baseMapper.selectOutPlanList(outPlan, page);
        return page.setRecords(outPlans);
    }

}
