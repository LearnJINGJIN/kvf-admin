package com.kalvin.kvf.modules.zg.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kalvin.kvf.modules.zg.service.OutCompanyService;
import org.springframework.stereotype.Service;
import com.kalvin.kvf.modules.zg.entity.OutCompany;
import com.kalvin.kvf.modules.zg.mapper.OutCompanyMapper;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 * @since 2020-12-17 11:26:13
 */
@Service
public class OutCompanyServiceImpl extends ServiceImpl<OutCompanyMapper, OutCompany> implements OutCompanyService {

    @Override
    public Page<OutCompany> listOutCompanyPage(OutCompany outCompany) {
        Page<OutCompany> page = new Page<>(outCompany.getCurrent(), outCompany.getSize());
        List<OutCompany> outCompanys = baseMapper.selectOutCompanyList(outCompany, page);
        return page.setRecords(outCompanys);
    }

}
