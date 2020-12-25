package com.kalvin.kvf.modules.zg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kalvin.kvf.modules.zg.entity.SystemDb;

import java.util.List;

/**
 * <p>
 * 系统数据库环境管理 Mapper 接口
 * </p>
 * @since 2020-12-24 16:56:21
 */
public interface SystemDbMapper extends BaseMapper<SystemDb> {

    /**
     * 查询列表(分页)
     * @param systemDb 查询参数
     * @param page 分页参数
     * @return list
     */
    List<SystemDb> selectSystemDbList(SystemDb systemDb, IPage page);

    SystemDb selectInfoById(Long id);

}
