package com.kalvin.kvf.modules.zg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kalvin.kvf.modules.zg.entity.SystemRelationship;

import java.util.List;

/**
 * <p>
 * 系统访问关系 Mapper 接口
 * </p>
 * @since 2020-12-23 14:52:34
 */
public interface SystemRelationshipMapper extends BaseMapper<SystemRelationship> {

    /**
     * 查询列表(分页)
     * @param systemRelationship 查询参数
     * @param page 分页参数
     * @return list
     */
    List<SystemRelationship> selectSystemRelationshipList(SystemRelationship systemRelationship, IPage page);

    SystemRelationship selectInfoById(Long id);


}
