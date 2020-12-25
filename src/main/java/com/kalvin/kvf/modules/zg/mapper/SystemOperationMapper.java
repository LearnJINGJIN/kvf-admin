package com.kalvin.kvf.modules.zg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kalvin.kvf.modules.zg.entity.SystemOperation;

import java.util.List;

/**
 * <p>
 * 系统操作管理 Mapper 接口
 * </p>
 * @since 2020-12-25 09:50:57
 */
public interface SystemOperationMapper extends BaseMapper<SystemOperation> {

    /**
     * 查询列表(分页)
     * @param systemOperation 查询参数
     * @param page 分页参数
     * @return list
     */
    List<SystemOperation> selectSystemOperationList(SystemOperation systemOperation, IPage page);
    SystemOperation selectInfoById(Long id);
}
