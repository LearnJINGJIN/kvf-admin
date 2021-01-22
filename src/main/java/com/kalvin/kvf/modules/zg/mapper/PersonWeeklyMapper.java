package com.kalvin.kvf.modules.zg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kalvin.kvf.modules.zg.entity.PersonWeekly;

import java.util.List;

/**
 * <p>
 * 驻场外包人员周报管理 Mapper 接口
 * </p>
 * @since 2021-01-21 17:22:57
 */
public interface PersonWeeklyMapper extends BaseMapper<PersonWeekly> {

    /**
     * 查询列表(分页)
     * @param personWeekly 查询参数
     * @param page 分页参数
     * @return list
     */
    List<PersonWeekly> selectPersonWeeklyList(PersonWeekly personWeekly, IPage page);

}
