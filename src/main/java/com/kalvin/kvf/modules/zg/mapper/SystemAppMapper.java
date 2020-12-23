package com.kalvin.kvf.modules.zg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kalvin.kvf.modules.zg.entity.SystemApp;

import java.util.List;

/**
 * <p>
 * 系统应用环境管理 Mapper 接口
 * </p>
 * @since 2020-12-22 11:29:38
 */
public interface SystemAppMapper extends BaseMapper<SystemApp> {

    /**
     * 查询列表(分页)
     * @param systemApp 查询参数
     * @param page 分页参数
     * @return list
     */
    List<SystemApp> selectSystemAppList(SystemApp systemApp, IPage page);

    SystemApp selectInfoById(Long id);
}
