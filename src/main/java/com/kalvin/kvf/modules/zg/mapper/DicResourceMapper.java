package com.kalvin.kvf.modules.zg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kalvin.kvf.modules.zg.entity.DicResource;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 * @since 2020-12-17 10:26:21
 */
public interface DicResourceMapper extends BaseMapper<DicResource> {

    /**
     * 查询列表(分页)
     * @param dicResource 查询参数
     * @param page 分页参数
     * @return list
     */
    List<DicResource> selectDicResourceList(DicResource dicResource, IPage page);

}
