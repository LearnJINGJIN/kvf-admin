package com.kalvin.kvf.modules.zg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kalvin.kvf.modules.zg.entity.System;

import java.util.List;

/**
 * <p>
 * 系统基本表 Mapper 接口
 * </p>
 * @since 2020-12-15 17:20:28
 */
public interface SystemMapper extends BaseMapper<System> {

    /**
     * 查询列表(分页)
     * @param system 查询参数
     * @param page 分页参数
     * @return list
     */
    List<System> selectSystemList(System system, IPage page);
    /**
     * 查询列表(分页)
     * @param id 查询参数
      * @return list
     */
    System selectInfoById(Long id);

}
