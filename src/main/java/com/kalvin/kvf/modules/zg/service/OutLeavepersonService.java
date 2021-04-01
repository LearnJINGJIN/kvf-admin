package com.kalvin.kvf.modules.zg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.modules.zg.entity.OutLeaveperson;

/**
 * <p>
 * 外包人员离场登记表 服务类
 * </p>
 * @since 2021-03-16 11:21:39
 */
public interface OutLeavepersonService extends IService<OutLeaveperson> {

    /**
     * 获取列表。分页
     * @param outLeaveperson 查询参数
     * @return page
     */
    Page<OutLeaveperson> listOutLeavepersonPage(OutLeaveperson outLeaveperson);

    /**
     * 根据id获取单条数据
     * @param id
     * @return
     */
    OutLeaveperson  getLeavePersonById(Long id);
    /**
     * 根据流程id获取单条数据
     * @param processInstanceId
     * @return
     */
    OutLeaveperson  getLeavePersonByprocess(String processInstanceId);

    /**
     * 新增并启动流程
     * @param outLeaveperson
     * @return
     */
    R startForm(OutLeaveperson outLeaveperson);
}
