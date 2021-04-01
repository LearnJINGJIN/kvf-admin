package com.kalvin.kvf.modules.zg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kalvin.kvf.modules.zg.entity.TransferList;

import java.util.List;

/**
 * <p>
 * 交接清单表 Mapper 接口
 * </p>
 * @since 2021-03-16 11:41:47
 */
public interface TransferListMapper extends BaseMapper<TransferList> {

    /**
     * 查询列表(分页)
     * @param transferList 查询参数
     * @param page 分页参数
     * @return list
     */
    List<TransferList> selectTransferListList(TransferList transferList, IPage page);

}
