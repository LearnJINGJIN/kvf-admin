package com.kalvin.kvf.modules.mts.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kalvin.kvf.modules.mts.entity.CRDReqData;
import com.kalvin.kvf.modules.mts.mapper.CRDReqDataMapper;
import com.kalvin.kvf.modules.mts.service.CRDReqDataService;
import com.kalvin.kvf.modules.zgbank.entity.CertificateMain;
import com.kalvin.kvf.modules.zgbank.mapper.CertificateMainMapper;
import com.kalvin.kvf.modules.zgbank.service.CertificateMainService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 *
 * @Auther: jingjin
 * @Date: 2020-10-22 15:36
 * @Modified by:
 */
@Service
public class CRDReqDataServiceImpl extends ServiceImpl<CRDReqDataMapper, CRDReqData> implements CRDReqDataService {
    @Override
    public List<CRDReqData> queryData(String date, String cardTypeCode) {
        return baseMapper.queryData(date,cardTypeCode);
    }

    @Override
    public CRDReqData queryCRDReqData(String refno) {
        return baseMapper.queryCRDReqData(refno);
    }
}
