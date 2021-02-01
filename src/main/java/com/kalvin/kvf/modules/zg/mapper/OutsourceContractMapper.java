package com.kalvin.kvf.modules.zg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kalvin.kvf.modules.zg.entity.OutsourceContract;

import java.util.List;

/**
 * <p>
 * 合同管理 Mapper 接口
 * </p>
 * @since 2021-01-26 10:36:16
 */
public interface OutsourceContractMapper extends BaseMapper<OutsourceContract> {

    /**
     * 查询列表(分页)
     * @param outsourceContract 查询参数
     * @param page 分页参数
     * @return list
     */
    List<OutsourceContract> selectOutsourceContractList(OutsourceContract outsourceContract, IPage page);
    /**
     * 根据id查询

     * @return list
     */
    OutsourceContract selectOutsourceContractByid(Long id);

}
