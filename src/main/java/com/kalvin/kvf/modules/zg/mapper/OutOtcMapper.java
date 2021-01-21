package com.kalvin.kvf.modules.zg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kalvin.kvf.modules.zg.entity.OutOtc;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 * @since 2021-01-14 15:13:47
 */
public interface OutOtcMapper extends BaseMapper<OutOtc> {

    /**
     * 查询列表(分页)
     * @param outOtc 查询参数
     * @param page 分页参数
     * @return list
     */
    List<OutOtc> selectOutOtcList(OutOtc outOtc, IPage page);

    OutOtc selectInfoById(Long id);
}
