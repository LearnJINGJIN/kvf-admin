package com.kalvin.kvf.modules.zg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kalvin.kvf.modules.zg.entity.OutCompany;

/**
 * <p>
 *  服务类
 * </p>
 * @since 2020-12-17 11:26:13
 */
public interface OutCompanyService extends IService<OutCompany> {

    /**
     * 获取列表。分页
     * @param outCompany 查询参数
     * @return page
     */
    Page<OutCompany> listOutCompanyPage(OutCompany outCompany);

}
