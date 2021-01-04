package com.kalvin.kvf.modules.zg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kalvin.kvf.modules.zg.entity.Project;

import java.util.List;

/**
 * <p>
 * 项目/需求管理 Mapper 接口
 * </p>
 * @since 2020-12-29 11:22:59
 */
public interface ProjectMapper extends BaseMapper<Project> {

    /**
     * 查询列表(分页)
     * @param project 查询参数
     * @param page 分页参数
     * @return list
     */
    List<Project> selectProjectList(Project project, IPage page);

}
