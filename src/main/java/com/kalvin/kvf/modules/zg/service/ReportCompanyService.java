package com.kalvin.kvf.modules.zg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kalvin.kvf.modules.zg.entity.ReportCompany;

/**
 * <p>
 *  服务类
 * </p>
 * @since 2020-12-17 16:31:10
 */
public interface ReportCompanyService extends IService<ReportCompany> {

    /**
     * 获取列表。分页
     * @param reportCompany 查询参数
     * @return page
     */
    Page<ReportCompany> listReportCompanyPage(ReportCompany reportCompany);

}
