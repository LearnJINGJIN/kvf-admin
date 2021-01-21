package com.kalvin.kvf.modules.zg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kalvin.kvf.modules.zg.entity.OutsourceProject;

import java.util.List;

/**
 * <p>
 * 外包项目清单 Mapper 接口
 * </p>
 * @since 2021-01-14 09:59:41
 */
public interface OutsourceProjectMapper extends BaseMapper<OutsourceProject> {

    /**
     * 查询列表(分页)
     * @param outsourceProject 查询参数
     * @param page 分页参数
     * @return list
     */
    List<OutsourceProject> selectOutsourceProjectList(OutsourceProject outsourceProject, IPage page);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    OutsourceProject selectOutsourceProjectbyId(Long id);
}
