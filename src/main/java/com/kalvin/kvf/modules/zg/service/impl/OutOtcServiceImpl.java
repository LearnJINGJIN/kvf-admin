package com.kalvin.kvf.modules.zg.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.common.exception.KvfException;
import com.kalvin.kvf.common.utils.ShiroKit;
import com.kalvin.kvf.modules.workflow.service.api.IAutoProcessForm;
import com.kalvin.kvf.modules.zg.entity.OutOtc;
import com.kalvin.kvf.modules.zg.mapper.OutOtcMapper;
import com.kalvin.kvf.modules.zg.service.OutOtcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 * @since 2021-01-14 15:13:47
 */
@Slf4j
@Service
public class OutOtcServiceImpl extends ServiceImpl<OutOtcMapper, OutOtc> implements OutOtcService {
    @Autowired
    private IAutoProcessForm iAutoProcessForm;
    @Override
    public Page<OutOtc> listOutOtcPage(OutOtc outOtc) {
        Page<OutOtc> page = new Page<>(outOtc.getCurrent(), outOtc.getSize());
        List<OutOtc> outOtcs = baseMapper.selectOutOtcList(outOtc, page);
        return page.setRecords(outOtcs);
    }
    @Override
    public Page<OutOtc> listOtcLeavePage(OutOtc outOtc) {
        Page<OutOtc> page = new Page<>(outOtc.getCurrent(), outOtc.getSize());
        List<OutOtc> outOtcs = baseMapper.selectOtcLeaveList(outOtc, page);
        return page.setRecords(outOtcs);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public R startForm(OutOtc outOtc) {
        String deploymentId=outOtc.getDeploymentId();
        if(StringUtils.isEmpty(deploymentId)){
            throw new KvfException("该表单未关联绑定流程，无法新增");
        }
        //保存业务数据
        outOtc.setDeploymentId(null);
        baseMapper.insert(outOtc);
        String processInstanceId=iAutoProcessForm.startFistFlow(deploymentId,String.valueOf(outOtc.getId()));
        //更新业务数据
        outOtc.setProcessInstanceId(processInstanceId);
        outOtc.setProcessStatus(1);
        baseMapper.updateById(outOtc);
        return R.ok("用户"+ShiroKit.getUser().getUsername()+"启动流程"+processInstanceId+"实例");
    }

    @Override
    public OutOtc getInfoById(Long id) {
        return baseMapper.selectInfoById(id);
    }

    @Override
    public OutOtc getInfoByProcess(String processInstanceId) {
        return baseMapper.selectInfoByProcess(processInstanceId);
    }


}
