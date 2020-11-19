package com.kalvin.kvf.modules.mts.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kalvin.kvf.modules.mts.entity.CRXReqData;

import java.util.List;

/**
 * Description:
 *
 * @Auther: jingjin
 * @Date: 2020-10-19 15:56
 * @Modified by:
 */
public interface CRXReqDataMapper extends BaseMapper<CRXReqData> {
    /**
     * 查询消费信息
     * @param date
     * @param cardTypeCode 1-消费借记数据 2-消费贷记数据 3-消费准贷记卡数据
     * @return
     */
     List<CRXReqData> queryData(String date, String cardTypeCode);


     CRXReqData queryCRXReqData(String refno);
}
