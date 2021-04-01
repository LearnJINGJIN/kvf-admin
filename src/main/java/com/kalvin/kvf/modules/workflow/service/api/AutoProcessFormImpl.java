package com.kalvin.kvf.modules.workflow.service.api;

import cn.hutool.core.util.StrUtil;
import com.kalvin.kvf.common.exception.KvfException;
import com.kalvin.kvf.common.utils.ShiroKit;
import com.kalvin.kvf.modules.workflow.dto.FlowData;
import com.kalvin.kvf.modules.workflow.dto.NodeAssignee;
import com.kalvin.kvf.modules.workflow.dto.ProcessNode;
import com.kalvin.kvf.modules.workflow.entity.ProcessForm;
import com.kalvin.kvf.modules.workflow.service.ProcessFormService;
import com.kalvin.kvf.modules.workflow.utils.ProcessKit;
import com.kalvin.kvf.modules.zg.entity.OutApplyleave;
import com.kalvin.kvf.modules.zg.entity.OutLeaveperson;
import com.kalvin.kvf.modules.zg.entity.OutOtc;
import com.kalvin.kvf.modules.zg.service.OutApplyleaveService;
import com.kalvin.kvf.modules.zg.service.OutLeavepersonService;
import com.kalvin.kvf.modules.zg.service.OutOtcService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
    @Autowired
    private OutApplyleaveService outApplyleaveService;
    @Autowired
    private OutLeavepersonService outLeavepersonService;
    @Resource
    private ProcessFormService processFormService;
    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;
    @Override
    public OutOtc getOutOtcFormConfig(String processInstanceId) {
        OutOtc outOtc = outOtcService.getInfoByProcess(processInstanceId);
        if(outOtc==null){
            throw new KvfException("异常：流程数据被删除!");
        }
        outOtc.setTheme("外包人员入场申请");
         return outOtc;
    }

    @Override
    public boolean updateOutApplyLeaveId(OutApplyleave outApplyLeave) {
        return outApplyleaveService.updateById(outApplyLeave);
    }

    @Override
    public OutApplyleave getOutApplyLeaveFormConfig(String processInstanceId) {
        OutApplyleave outApplyleave=outApplyleaveService.getOutApplyleaveByProcess(processInstanceId);
        if(outApplyleave==null){
            throw new KvfException("异常：流程数据被删除!");
        }
        outApplyleave.setTheme("请假申请");
        return outApplyleave;
    }
    @Override
    public boolean updatetLeavePersonById(OutLeaveperson outLeaveperson) {
        return outLeavepersonService.updateById(outLeaveperson);
    }
    @Override
    public OutLeaveperson getLeavePersonFormConfig(String processInstanceId) {
        OutLeaveperson outLeaveperson = outLeavepersonService.getLeavePersonByprocess(processInstanceId);
        if (outLeaveperson==null){
            throw new KvfException("异常：流程数据被删除!");
        }
        outLeaveperson.setTheme("离场申请");
         return outLeaveperson;
    }

    @Override
    public String startFistFlow(String deploymentId,String businessId) {
        final HashMap<String, Object> taskVariables = new HashMap<>();
        String startUser= ShiroKit.getUser().getUsername();
        //发起流程，并建立关系
        ProcessForm processForm = processFormService.getByModelId(ProcessKit.getModel(deploymentId).getId());
        if (processForm == null) {
            throw new KvfException("该流程未关联绑定流程表单，无法使用");
        }
        if (StrUtil.isBlank(processForm.getFormAddrs())) {
            throw new KvfException("关联表单不能为空");
        }
        final FlowData flowData = new FlowData();
        flowData.setMainFormKey(processForm.getFormCode());
        flowData.setBusinessId(businessId);
        flowData.setStartUser(startUser);
        flowData.setCurrentUser(startUser);
        flowData.setFirstNode(true);
        flowData.setFirstSubmit(true);
        flowData.setFormKey(processForm.getFormAddrs());
        taskVariables.put(ProcessKit.FLOW_DATA_KEY, flowData);
        taskVariables.put("startUser", startUser);
        // 设置当前任务的办理人  // TODO: 2020/4/22 不知是否有用？ FLOW_DATA_KEY.FlowData.startUser
        Authentication.setAuthenticatedUserId(startUser);
        ProcessDefinition processDefinition = ProcessKit.getProcessDefinition(deploymentId);
        if (processDefinition.isSuspended()) {
            throw new KvfException("该流程已被挂起，无法使用，请激活后使用");
        }
        String processDefinitionId = processDefinition.getId();
        ProcessInstance processInstance=runtimeService.startProcessInstanceById(processDefinitionId, businessId, taskVariables);
        // 设置流程启动后的相关核心变量
        String processName = processDefinition.getName();
        String processInstanceId = processInstance.getProcessInstanceId();

        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        String taskId = task.getId();
        // 设置流程实例名称
        runtimeService.setProcessInstanceName(processInstanceId, processName);
        if (flowData.isFirstSubmit()) {
            flowData.setFirstSubmitTime(new Date());
        }
        flowData.setProcessName(processName);
        flowData.setProcessDefinitionId(processDefinitionId);
        flowData.setProcessInstanceId(processInstanceId);
        flowData.setFirstNodeId(task.getTaskDefinitionKey());
        flowData.setCurrentNodeId(task.getTaskDefinitionKey());
        flowData.setCurrentNodeName(task.getName());
        flowData.setTaskId(taskId);
        flowData.setExecutionId(task.getExecutionId());
        flowData.setFirstNode(false);
        flowData.setFirstSubmit(false);
        flowData.setPass(true);
        // 储存流程核心流转变量
        taskVariables.put(ProcessKit.FLOW_DATA_KEY, flowData);
        // 储存流程表单流转数据
        taskVariables.put(ProcessKit.FLOW_VARIABLES_KEY, new HashMap<>());
        // 添加审批意见
        taskService.addComment(taskId, processInstanceId, ProcessKit.DEFAULT_AGREE_COMMENT );
        taskService.setAssignee(taskId, "");
        taskService.setAssignee(taskId, startUser);
        // 设置下一步路由出口用户任务节点集
        List<UserTask> nextUserTask = ProcessKit.getNextNode(processDefinitionId, flowData.getCurrentNodeId(), new HashMap<>(0));
        List<ProcessNode> processNodes = ProcessKit.convertTo(nextUserTask);
        String nextUser=processNodes.get(0).getAssignee();
        if (StrUtil.isBlank(nextUser) && processNodes.size() != 0) {
            throw new KvfException("下一环节审批人不允许为空");
        }
        flowData.setNextNodes(processNodes);
        flowData.setNextNodeNum(processNodes.size());
        flowData.setNextUser(nextUser);
        flowData.setTargetNodeId(processNodes.get(0).getNodeId());
        // 设置当前环节可驳回的所有任务节点集
        List<ProcessNode> canBackNodes = ProcessKit.getCanBackNodes(flowData.getCurrentNodeId(), processDefinitionId);
        flowData.setCanBackNodes(canBackNodes);
        // 记录每个实例任务节点审批人
        HashMap<String, NodeAssignee> nodeAssignee = flowData.getNodeAssignee();
        if (nodeAssignee == null) {
            nodeAssignee = new HashMap<>();
        }
        nodeAssignee.put(flowData.getCurrentNodeId() + "_" + taskId, new NodeAssignee(taskId, flowData.getCurrentNodeId(), startUser));
        flowData.setNodeAssignee(nodeAssignee);
        // 正式提交任务
        taskService.complete(taskId, taskVariables);
        log.debug("用户【{}】启动流程【{}】实例【{}】", startUser, processName, processInstance.getId());
        return processInstanceId;
    }

    @Override
    public boolean updateOtcById(OutOtc outOtc) {
        return outOtcService.updateById(outOtc);
    }
}
