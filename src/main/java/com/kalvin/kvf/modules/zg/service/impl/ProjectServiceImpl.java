package com.kalvin.kvf.modules.zg.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kalvin.kvf.modules.zg.service.ProjectService;
import org.springframework.stereotype.Service;
import com.kalvin.kvf.modules.zg.entity.Project;
import com.kalvin.kvf.modules.zg.mapper.ProjectMapper;

import java.util.List;

/**
 * <p>
 * 项目/需求管理 服务实现类
 * </p>
 * @since 2020-12-29 11:22:59
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

    @Override
    public Page<Project> listProjectPage(Project project) {
        Page<Project> page = new Page<>(project.getCurrent(), project.getSize());
        List<Project> projects = baseMapper.selectProjectList(project, page);
        return page.setRecords(projects);
    }

}
