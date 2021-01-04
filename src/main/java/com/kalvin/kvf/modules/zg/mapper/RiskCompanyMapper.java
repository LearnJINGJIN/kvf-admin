package com.kalvin.kvf.modules.zg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kalvin.kvf.modules.zg.entity.RiskCompany;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 * @since 2020-12-25 09:53:24
 */
public interface RiskCompanyMapper extends BaseMapper<RiskCompany> {

    /**
     * 查询列表(分页)
     * @param riskCompany 查询参数
     * @param page 分页参数
     * @return list
     */
    List<RiskCompany> selectRiskCompanyList(RiskCompany riskCompany, IPage page);
    RiskCompany selectInfoById(Long id);
}
