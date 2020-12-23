package com.kalvin.kvf.modules.zg.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kalvin.kvf.modules.zg.service.RiskCompanyService;
import org.springframework.stereotype.Service;
import com.kalvin.kvf.modules.zg.entity.RiskCompany;
import com.kalvin.kvf.modules.zg.mapper.RiskCompanyMapper;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 * @since 2020-12-17 11:53:25
 */
@Service
public class RiskCompanyServiceImpl extends ServiceImpl<RiskCompanyMapper, RiskCompany> implements RiskCompanyService {

    @Override
    public Page<RiskCompany> listRiskCompanyPage(RiskCompany riskCompany) {
        Page<RiskCompany> page = new Page<>(riskCompany.getCurrent(), riskCompany.getSize());
        List<RiskCompany> riskCompanys = baseMapper.selectRiskCompanyList(riskCompany, page);
        return page.setRecords(riskCompanys);
    }

}
