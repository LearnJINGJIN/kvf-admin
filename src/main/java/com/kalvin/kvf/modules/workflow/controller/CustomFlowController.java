package com.kalvin.kvf.modules.workflow.controller;

import com.kalvin.kvf.modules.workflow.service.FormService;
import com.kalvin.kvf.modules.workflow.service.IWorkFlowService;
import com.kalvin.kvf.modules.workflow.service.ProcessFormService;
import com.kalvin.kvf.modules.workflow.service.api.IProcessEngine;
import com.kalvin.kvf.modules.workflow.service.api.IProcessImage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description:自定义工作流程
 *
 * @Auther: jingjin
 * @Date: 2021-02-22 15:40
 * @Modified by:
 */
@Slf4j
@RestController
@RequestMapping(value = "workflow")
public class CustomFlowController {
    @Autowired
    private IProcessEngine processEngine;

    @Resource
    private IProcessImage processImage;

    @Resource
    private FormService formService;

    @Resource
    private ProcessFormService processFormService;

    @Resource
    private IWorkFlowService workFlowService;
}
