package com.kalvin.kvf.modules.zg.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kalvin.kvf.modules.zg.service.OutApplyleaveService;
import org.springframework.stereotype.Service;
import com.kalvin.kvf.modules.zg.entity.OutApplyleave;
import com.kalvin.kvf.modules.zg.mapper.OutApplyleaveMapper;

import java.util.List;

/**
 * <p>
 * 外包请假管理 服务实现类
 * </p>
 * @since 2021-03-03 15:05:11
 */
@Service
public class OutApplyleaveServiceImpl extends ServiceImpl<OutApplyleaveMapper, OutApplyleave> implements OutApplyleaveService {

    @Override
    public Page<OutApplyleave> listOutApplyleavePage(OutApplyleave outApplyleave) {
        Page<OutApplyleave> page = new Page<>(outApplyleave.getCurrent(), outApplyleave.getSize());
        List<OutApplyleave> outApplyleaves = baseMapper.selectOutApplyleaveList(outApplyleave, page);
        return page.setRecords(outApplyleaves);
    }

    @Override
    public OutApplyleave getOutApplyleaveById(Long id) {
        return baseMapper.selectOutApplyleavebyId(id);
    }

}
