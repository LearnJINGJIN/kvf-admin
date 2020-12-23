package com.kalvin.kvf.modules.zg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kalvin.kvf.modules.zg.entity.OutCompany;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 * @since 2020-12-17 11:26:13
 */
public interface OutCompanyMapper extends BaseMapper<OutCompany> {

    /**
     * 查询列表(分页)
     * @param outCompany 查询参数
     * @param page 分页参数
     * @return list
     */
    List<OutCompany> selectOutCompanyList(OutCompany outCompany, IPage page);

}
