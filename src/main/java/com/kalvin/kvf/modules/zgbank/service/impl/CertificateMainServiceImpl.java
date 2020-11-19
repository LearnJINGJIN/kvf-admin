package com.kalvin.kvf.modules.zgbank.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kalvin.kvf.modules.zgbank.entity.CertificateMain;
import com.kalvin.kvf.modules.zgbank.mapper.CertificateMainMapper;
import com.kalvin.kvf.modules.zgbank.service.CertificateMainService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 证书管理表 服务实现类
 * </p>
 * @since 2020-09-28 16:23:00
 */
@Service
public class CertificateMainServiceImpl extends ServiceImpl<CertificateMainMapper, CertificateMain> implements CertificateMainService {

    @Override
    public Page<CertificateMain> listCertificateMainPage(CertificateMain certificateMain) {
        Page<CertificateMain> page = new Page<>(certificateMain.getCurrent(), certificateMain.getSize());
        List<CertificateMain> certificateMains = baseMapper.selectCertificateMainList(certificateMain, page);
        return page.setRecords(certificateMains);
    }

}
