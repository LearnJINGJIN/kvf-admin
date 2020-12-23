package com.kalvin.kvf.modules.zg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kalvin.kvf.modules.zg.entity.DocTemplate;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 * @since 2020-12-16 17:34:10
 */
public interface DocTemplateMapper extends BaseMapper<DocTemplate> {

    /**
     * 查询列表(分页)
     * @param docTemplate 查询参数
     * @param page 分页参数
     * @return list
     */
    List<DocTemplate> selectDocTemplateList(DocTemplate docTemplate, IPage page);

}
