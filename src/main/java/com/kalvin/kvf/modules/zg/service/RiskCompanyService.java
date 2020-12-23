package com.kalvin.kvf.modules.zg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kalvin.kvf.modules.zg.entity.RiskCompany;

/**
 * <p>
 *  服务类
 * </p>
 * @since 2020-12-17 11:53:25
 */
public interface RiskCompanyService extends IService<RiskCompany> {

    /**
     * 获取列表。分页
     * @param riskCompany 查询参数
     * @return page
     */
    Page<RiskCompany> listRiskCompanyPage(RiskCompany riskCompany);

}
