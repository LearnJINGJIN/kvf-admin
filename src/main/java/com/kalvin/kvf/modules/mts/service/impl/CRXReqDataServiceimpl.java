package com.kalvin.kvf.modules.mts.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kalvin.kvf.modules.mts.entity.CRXReqData;
import com.kalvin.kvf.modules.mts.mapper.CRXReqDataMapper;
import com.kalvin.kvf.modules.mts.service.CRXReqDataService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 *
 * @Auther: jingjin
 * @Date: 2020-10-22 17:13
 * @Modified by:
 */
@Service
public class CRXReqDataServiceimpl extends ServiceImpl<CRXReqDataMapper, CRXReqData> implements CRXReqDataService {
    @Override
    public List<CRXReqData> queryData(String date, String cardTypeCode) {
        return baseMapper.queryData(date, cardTypeCode);
    }

    @Override
    public CRXReqData queryCRXReqData(String refno) {
        return baseMapper.queryCRXReqData(refno);
    }
}
