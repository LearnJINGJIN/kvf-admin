package com.kalvin.kvf.modules.zg.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kalvin.kvf.common.utils.ShiroKit;
import com.kalvin.kvf.modules.zg.service.OutsourceJobService;
import org.springframework.stereotype.Service;
import com.kalvin.kvf.modules.zg.entity.OutsourceJob;
import com.kalvin.kvf.modules.zg.mapper.OutsourceJobMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 项目任务清单 服务实现类
 * </p>
 * @since 2021-01-14 17:26:45
 */
@Service
public class OutsourceJobServiceImpl extends ServiceImpl<OutsourceJobMapper, OutsourceJob> implements OutsourceJobService {

    @Override
    public Page<OutsourceJob> listOutsourceJobPage(OutsourceJob outsourceJob) {
        Page<OutsourceJob> page = new Page<>(outsourceJob.getCurrent(), outsourceJob.getSize());
        List<OutsourceJob> outsourceJobs = baseMapper.selectOutsourceJobList(outsourceJob, page);
        return page.setRecords(outsourceJobs);
    }

    @Override
    public OutsourceJob getOutsourceJobById(Long id) {
        return baseMapper.selectOutsourceJobById(id);    }


}
