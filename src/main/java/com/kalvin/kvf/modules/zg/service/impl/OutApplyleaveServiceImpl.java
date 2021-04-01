package com.kalvin.kvf.modules.zg.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.common.exception.KvfException;
import com.kalvin.kvf.common.utils.ShiroKit;
import com.kalvin.kvf.modules.workflow.service.api.IAutoProcessForm;
import com.kalvin.kvf.modules.zg.entity.OutApplyleave;
import com.kalvin.kvf.modules.zg.mapper.OutApplyleaveMapper;
import com.kalvin.kvf.modules.zg.service.OutApplyleaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 外包请假管理 服务实现类
 * </p>
 * @since 2021-03-03 15:05:11
 */
@Slf4j
@Service
public class OutApplyleaveServiceImpl extends ServiceImpl<OutApplyleaveMapper, OutApplyleave> implements OutApplyleaveService {

    @Autowired
    private IAutoProcessForm iAutoProcessForm;
    @Override
    public Page<OutApplyleave> listOutApplyleavePage(OutApplyleave outApplyleave) {
        Page<OutApplyleave> page = new Page<>(outApplyleave.getCurrent(), outApplyleave.getSize());
        List<OutApplyleave> outApplyleaves = baseMapper.selectOutApplyleaveList(outApplyleave, page);
        return page.setRecords(outApplyleaves);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public R startForm(OutApplyleave outApplyleave) {
        String deploymentId=outApplyleave.getDeploymentId();
        if(StringUtils.isEmpty(deploymentId)){
            throw new KvfException("该表单未关联绑定流程，无法新增");
        }
        //保存业务数据
        outApplyleave.setCreateUser(ShiroKit.getUserId());
        outApplyleave.setDeploymentId(null);
        baseMapper.insert(outApplyleave);
        String processInstanceId=iAutoProcessForm.startFistFlow(deploymentId,String.valueOf(outApplyleave.getId()));
        //更新业务数据
        outApplyleave.setProcessInstanceId(processInstanceId);
        outApplyleave.setProcessStatus(1);
        baseMapper.updateById(outApplyleave);
         return R.ok("用户"+ShiroKit.getUser().getUsername()+"启动流程"+processInstanceId+"实例");
    }

    @Override
    public OutApplyleave getOutApplyleaveById(Long id) {
        return baseMapper.selectOutApplyleavebyId(id);
    }
    @Override
    public OutApplyleave getOutApplyleaveByProcess(String processInstanceId) {
        return baseMapper.selectOutApplyleavebyProcess(processInstanceId);
    }

}
