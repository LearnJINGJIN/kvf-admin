package com.kalvin.kvf.modules.zgbank.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kalvin.kvf.modules.zgbank.entity.CertificateMain;

/**
 * <p>
 * 证书管理表 服务类
 * </p>
 * @since 2020-09-28 16:23:00
 */
public interface CertificateMainService extends IService<CertificateMain> {

    /**
     * 获取列表。分页
     * @param certificateMain 查询参数
     * @return page
     */
    Page<CertificateMain> listCertificateMainPage(CertificateMain certificateMain);

}
