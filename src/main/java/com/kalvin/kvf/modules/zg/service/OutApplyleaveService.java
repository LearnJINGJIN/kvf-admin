package com.kalvin.kvf.modules.zg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kalvin.kvf.modules.zg.entity.OutApplyleave;

/**
 * <p>
 * 外包请假管理 服务类
 * </p>
 * @since 2021-03-03 15:05:11
 */
public interface OutApplyleaveService extends IService<OutApplyleave> {

    /**
     * 获取列表。分页
     * @param outApplyleave 查询参数
     * @return page
     */
    Page<OutApplyleave> listOutApplyleavePage(OutApplyleave outApplyleave);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    OutApplyleave getOutApplyleaveById(Long id);
}
