package com.kalvin.kvf.modules.zg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kalvin.kvf.modules.zg.entity.MattersDetail;

import java.util.List;

/**
 * <p>
 * 具体事项完成明细表 Mapper 接口
 * </p>
 * @since 2021-01-05 16:22:27
 */
public interface MattersDetailMapper extends BaseMapper<MattersDetail> {

    /**
     * 查询列表(分页)
     * @param mattersDetail 查询参数
     * @param page 分页参数
     * @return list
     */
    List<MattersDetail> selectMattersDetailList(MattersDetail mattersDetail, IPage page);

}
