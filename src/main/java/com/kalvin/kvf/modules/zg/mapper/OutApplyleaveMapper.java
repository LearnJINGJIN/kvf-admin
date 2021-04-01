package com.kalvin.kvf.modules.zg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kalvin.kvf.modules.zg.entity.OutApplyleave;

import java.util.List;

/**
 * <p>
 * 外包请假管理 Mapper 接口
 * </p>
 * @since 2021-03-03 15:05:11
 */
public interface OutApplyleaveMapper extends BaseMapper<OutApplyleave> {

    /**
     * 查询列表(分页)
     * @param outApplyleave 查询参数
     * @param page 分页参数
     * @return list
     */
    List<OutApplyleave> selectOutApplyleaveList(OutApplyleave outApplyleave, IPage page);
    /**
     * 查询单条数据
     * @param id 查询参数
      * @return list
     */
    OutApplyleave selectOutApplyleavebyId(Long id);
    /**
     * 查询单条数据
     * @param processInstanceId 查询参数
      * @return list
     */
    OutApplyleave selectOutApplyleavebyProcess(String processInstanceId);

}
