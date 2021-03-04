package com.kalvin.kvf.modules.workflow.service.api;

import com.kalvin.kvf.common.exception.KvfException;
import com.kalvin.kvf.modules.workflow.utils.ProcessKit;
import com.kalvin.kvf.modules.zg.entity.OutOtc;
import com.kalvin.kvf.modules.zg.service.OutOtcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @Auther: jingjin
 * @Date: 2021-02-24 16:14
 * @Modified by:
 */
@Slf4j
@Service
public class AutoProcessFormImpl implements IAutoProcessForm{
    @Autowired
    private OutOtcService outOtcService;
    @Override
    public OutOtc getOutOtcFormConfig(String processInstanceId) {
        Map<String,Object> hashMap=new HashMap<>();
        OutOtc outOtc = outOtcService.getInfoByProcess(processInstanceId);
        if(outOtc==null){
            throw new KvfException("异常：流程数据被删除!");
        }
        outOtc.setTheme("外包人员入场申请");
         return outOtc;
    }

    @Override
    public boolean updateOtcById(OutOtc outOtc) {
        return outOtcService.updateById(outOtc);
    }
}
