package com.kalvin.kvf.modules.zg.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kalvin.kvf.modules.zg.service.OutsourceProjectService;
import org.springframework.stereotype.Service;
import com.kalvin.kvf.modules.zg.entity.OutsourceProject;
import com.kalvin.kvf.modules.zg.mapper.OutsourceProjectMapper;

import java.util.List;

/**
 * <p>
 * 外包项目清单 服务实现类
 * </p>
 * @since 2021-01-14 09:59:41
 */
@Service
public class OutsourceProjectServiceImpl extends ServiceImpl<OutsourceProjectMapper, OutsourceProject> implements OutsourceProjectService {

    @Override
    public Page<OutsourceProject> listOutsourceProjectPage(OutsourceProject outsourceProject) {
        Page<OutsourceProject> page = new Page<>(outsourceProject.getCurrent(), outsourceProject.getSize());
        List<OutsourceProject> outsourceProjects = baseMapper.selectOutsourceProjectList(outsourceProject, page);
        return page.setRecords(outsourceProjects);
    }

    @Override
    public OutsourceProject getOutsourceProjectById(Long id) {
        return baseMapper.selectOutsourceProjectbyId(id);
    }

}
