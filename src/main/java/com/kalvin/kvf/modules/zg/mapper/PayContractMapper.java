package com.kalvin.kvf.modules.zg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kalvin.kvf.modules.zg.entity.PayContract;

import java.util.List;

/**
 * <p>
 * 合同-付款对应表 Mapper 接口
 * </p>
 * @since 2021-01-27 14:44:04
 */
public interface PayContractMapper extends BaseMapper<PayContract> {

    /**
     * 查询列表(分页)
     * @param payContract 查询参数
     * @param page 分页参数
     * @return list
     */
    List<PayContract> selectPayContractList(PayContract payContract, IPage page);

}
