package com.kalvin.kvf.modules.mts.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kalvin.kvf.modules.mts.entity.CRDReqData;

import java.util.List;

/**
 * Description:
 *
 * @Auther: jingjin
 * @Date: 2020-10-22 15:35
 * @Modified by:
 */
public interface CRDReqDataService extends IService<CRDReqData>{
    /**
     * 查询提现信息
     * @param date
     * @param cardTypeCode 1-借记卡， 2-贷记卡， 3-准贷记卡
     * @return
     */
    List<CRDReqData> queryData(String date, String cardTypeCode);

    CRDReqData queryCRDReqData(String refno);
}
