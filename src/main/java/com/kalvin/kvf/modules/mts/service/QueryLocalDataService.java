package com.kalvin.kvf.modules.mts.service;

import com.kalvin.kvf.modules.mts.entity.CRDReqData;
import com.kalvin.kvf.modules.mts.entity.CRXReqData;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:mts数据库查询
 *
 * @Auther: jingjin
 * @Date: 2020-10-19 15:05
 * @Modified by:
 */
public interface QueryLocalDataService {
    /**
     * 查询提现数据
     * @param date
     * @param cardTypeCode
     * @return
     */
     List<CRDReqData> queryCRDDBITData(String date, String cardTypeCode) ;

    /**
     * 查询消费数据
     */
    List<CRXReqData> queryCRXDBITData(String date, String cardTypeCode) ;
    /**
     * 根据业务参号查询消费信息
     * @param refno
     * @return
     */
    CRXReqData getCRXReqData(String refno);
    /**
     * 根据业务参号查询提现信息
     * @param refno
     * @return
     */
     CRDReqData getCRDReqData(String refno);
     int insertCrd(CRDReqData crd);
     int insertCrx(CRXReqData crx);
}
