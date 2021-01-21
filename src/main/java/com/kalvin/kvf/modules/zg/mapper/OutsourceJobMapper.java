package com.kalvin.kvf.modules.zg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kalvin.kvf.modules.zg.entity.OutsourceJob;
import com.kalvin.kvf.modules.zg.entity.OutsourceProject;

import java.util.List;

/**
 * <p>
 * 项目任务清单 Mapper 接口
 * </p>
 * @since 2021-01-14 17:26:45
 */
public interface OutsourceJobMapper extends BaseMapper<OutsourceJob> {

    /**
     * 查询列表(分页)
     * @param outsourceJob 查询参数
     * @param page 分页参数
     * @return list
     */
    List<OutsourceJob> selectOutsourceJobList(OutsourceJob outsourceJob, IPage page);
    /**
     * 通过id查询
     * @param id
     * @return
     */
    OutsourceJob selectOutsourceJobById(Long id);
}
