package com.kalvin.kvf.modules.zg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kalvin.kvf.modules.zg.entity.OutsourceProject;

/**
 * <p>
 * 外包项目清单 服务类
 * </p>
 * @since 2021-01-14 09:59:41
 */
public interface OutsourceProjectService extends IService<OutsourceProject> {

    /**
     * 获取列表。分页
     * @param outsourceProject 查询参数
     * @return page
     */
    Page<OutsourceProject> listOutsourceProjectPage(OutsourceProject outsourceProject);
    OutsourceProject  getOutsourceProjectById(Long id);
}
