package com.kalvin.kvf.modules.mts.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kalvin.kvf.modules.mts.entity.CRDReqData;

import java.util.List;

/**
 * Description:银行卡境外提现-接口控制数据对象
 *
 * @Auther: jingjin
 * @Date: 2020-10-19 15:07
 * @Modified by:
 */
public interface CRDReqDataMapper extends BaseMapper<CRDReqData> {
    /**
     * 查询提现信息
     * @param date
     * @param cardTypeCode 1-借记卡， 2-贷记卡， 3-准贷记卡
     * @return
     */
     List<CRDReqData> queryData(String date, String cardTypeCode);

     CRDReqData queryCRDReqData(String refno);
}
