package com.kalvin.kvf.modules.zg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kalvin.kvf.modules.zg.entity.OutPerson;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 * @since 2020-12-17 11:48:19
 */
public interface OutPersonMapper extends BaseMapper<OutPerson> {

    /**
     * 查询列表(分页)
     * @param outPerson 查询参数
     * @param page 分页参数
     * @return list
     */
    List<OutPerson> selectOutPersonList(OutPerson outPerson, IPage page);

}
