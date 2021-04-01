package com.kalvin.kvf.modules.zg.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.common.exception.KvfException;
import com.kalvin.kvf.common.utils.ShiroKit;
import com.kalvin.kvf.modules.workflow.service.api.IAutoProcessForm;
import com.kalvin.kvf.modules.zg.entity.OutLeaveperson;
import com.kalvin.kvf.modules.zg.mapper.OutLeavepersonMapper;
import com.kalvin.kvf.modules.zg.service.OutLeavepersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 外包人员离场登记表 服务实现类
 * </p>
 * @since 2021-03-16 11:21:39
 */
@Service
public class OutLeavepersonServiceImpl extends ServiceImpl<OutLeavepersonMapper, OutLeaveperson> implements OutLeavepersonService {
    @Autowired
    private IAutoProcessForm iAutoProcessForm;
    @Override
    public Page<OutLeaveperson> listOutLeavepersonPage(OutLeaveperson outLeaveperson) {
        Page<OutLeaveperson> page = new Page<>(outLeaveperson.getCurrent(), outLeaveperson.getSize());
        List<OutLeaveperson> outLeavepersons = baseMapper.selectOutLeavepersonList(outLeaveperson, page);
        return page.setRecords(outLeavepersons);
    }

    @Override
    public OutLeaveperson getLeavePersonByprocess(String processInstanceId) {
        return baseMapper.selectLeavePersonByProcess(processInstanceId);
    }

    @Override
    public OutLeaveperson getLeavePersonById(Long id) {
        return baseMapper.selectLeavePersonById(id);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public R startForm(OutLeaveperson outLeaveperson) {
        String deploymentId=outLeaveperson.getDeploymentId();
        if(StringUtils.isEmpty(deploymentId)){
            throw new KvfException("该表单未关联绑定流程，无法新增");
        }
        //保存业务数据
        outLeaveperson.setCreateUser(ShiroKit.getUserId());
        outLeaveperson.setDeploymentId(null);
        baseMapper.insert(outLeaveperson);
        String processInstanceId=iAutoProcessForm.startFistFlow(deploymentId,String.valueOf(outLeaveperson.getId()));
        //更新业务数据
        outLeaveperson.setProcessInstanceId(processInstanceId);
        outLeaveperson.setProcessStatus(1);
        baseMapper.updateById(outLeaveperson);
        return R.ok("用户"+ShiroKit.getUser().getUsername()+"启动流程"+processInstanceId+"实例");
    }
}
