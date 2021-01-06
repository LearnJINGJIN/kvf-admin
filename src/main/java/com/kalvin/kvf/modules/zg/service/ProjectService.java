package com.kalvin.kvf.modules.zg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kalvin.kvf.modules.zg.entity.Project;

/**
 * <p>
 * 项目/需求管理 服务类
 * </p>
 * @since 2020-12-29 11:22:59
 */
public interface ProjectService extends IService<Project> {

    /**
     * 获取列表。分页
     * @param project 查询参数
     * @return page
     */
    Page<Project> listProjectPage(Project project);

}
