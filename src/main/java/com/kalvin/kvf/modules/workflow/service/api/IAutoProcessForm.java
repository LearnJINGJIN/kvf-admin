package com.kalvin.kvf.modules.workflow.service.api;

import com.kalvin.kvf.modules.zg.entity.OutOtc;

import java.util.Map;

/**
 * Description:通过流程id获取自定义表单数据
 *
 * @Auther: jingjin
 * @Date: 2021-02-24 16:14
 * @Modified by:
 */
public interface IAutoProcessForm {
    /**
     * 获取外包人员入场申请数据
     * @param processInstanceId
     * @return
     */
    OutOtc getOutOtcFormConfig(String processInstanceId);
    /**
     * 更新流程otc
      * @return
     */
    boolean updateOtcById(OutOtc outOtc);
}
