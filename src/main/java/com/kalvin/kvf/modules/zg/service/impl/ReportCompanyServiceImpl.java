package com.kalvin.kvf.modules.zg.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kalvin.kvf.modules.zg.service.ReportCompanyService;
import org.springframework.stereotype.Service;
import com.kalvin.kvf.modules.zg.entity.ReportCompany;
import com.kalvin.kvf.modules.zg.mapper.ReportCompanyMapper;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 * @since 2020-12-17 16:31:10
 */
@Service
public class ReportCompanyServiceImpl extends ServiceImpl<ReportCompanyMapper, ReportCompany> implements ReportCompanyService {

    @Override
    public Page<ReportCompany> listReportCompanyPage(ReportCompany reportCompany) {
        Page<ReportCompany> page = new Page<>(reportCompany.getCurrent(), reportCompany.getSize());
        List<ReportCompany> reportCompanys = baseMapper.selectReportCompanyList(reportCompany, page);
        return page.setRecords(reportCompanys);
    }

}
