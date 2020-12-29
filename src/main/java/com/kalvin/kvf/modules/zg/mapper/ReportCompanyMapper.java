package com.kalvin.kvf.modules.zg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kalvin.kvf.modules.zg.entity.ReportCompany;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 * @since 2020-12-17 16:31:10
 */
public interface ReportCompanyMapper extends BaseMapper<ReportCompany> {

    /**
     * 查询列表(分页)
     * @param reportCompany 查询参数
     * @param page 分页参数
     * @return list
     */
    List<ReportCompany> selectReportCompanyList(ReportCompany reportCompany, IPage page);
    ReportCompany selectInfoById(Long id);
}
