package com.kalvin.kvf.modules.zg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kalvin.kvf.modules.zg.entity.OutLeaveperson;

import java.util.List;

/**
 * <p>
 * 外包人员离场登记表 Mapper 接口
 * </p>
 * @since 2021-03-16 11:21:39
 */
public interface OutLeavepersonMapper extends BaseMapper<OutLeaveperson> {

    /**
     * 查询列表(分页)
     * @param outLeaveperson 查询参数
     * @param page 分页参数
     * @return list
     */
    List<OutLeaveperson> selectOutLeavepersonList(OutLeaveperson outLeaveperson, IPage page);

    /**
     * 更具
     * @param id
     * @return
     */
    OutLeaveperson selectLeavePersonById(Long id);
    /**
     * 更具流程id获取
     * @param processInstanceId
     * @return
     */
    OutLeaveperson selectLeavePersonByProcess(String processInstanceId);
}
