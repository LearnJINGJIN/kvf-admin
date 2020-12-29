package com.kalvin.kvf.modules.zg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kalvin.kvf.modules.zg.entity.SvnSystem;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 * @since 2020-12-15 14:48:46
 */
public interface SvnSystemMapper extends BaseMapper<SvnSystem> {

    /**
     * 查询列表(分页)
     * @param svnSystem 查询参数
     * @param page 分页参数
     * @return list
     */
    List<SvnSystem> selectSvnSystemList(SvnSystem svnSystem, IPage page);

    SvnSystem selectInfoById(Long id);
}
