package com.kalvin.kvf.modules.zgbank.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kalvin.kvf.modules.zgbank.entity.CertificateMain;

import java.util.List;

/**
 * <p>
 * 证书管理表 Mapper 接口
 * </p>
 * @since 2020-09-28 16:23:00
 */
public interface CertificateMainMapper extends BaseMapper<CertificateMain> {

    /**
     * 查询列表(分页)
     * @param certificateMain 查询参数
     * @param page 分页参数
     * @return list
     */
    List<CertificateMain> selectCertificateMainList(CertificateMain certificateMain, IPage page);

}
