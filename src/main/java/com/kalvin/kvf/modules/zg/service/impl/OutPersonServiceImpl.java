package com.kalvin.kvf.modules.zg.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kalvin.kvf.modules.zg.service.OutPersonService;
import org.springframework.stereotype.Service;
import com.kalvin.kvf.modules.zg.entity.OutPerson;
import com.kalvin.kvf.modules.zg.mapper.OutPersonMapper;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 * @since 2020-12-17 11:48:19
 */
@Service
public class OutPersonServiceImpl extends ServiceImpl<OutPersonMapper, OutPerson> implements OutPersonService {

    @Override
    public Page<OutPerson> listOutPersonPage(OutPerson outPerson) {
        Page<OutPerson> page = new Page<>(outPerson.getCurrent(), outPerson.getSize());
        List<OutPerson> outPersons = baseMapper.selectOutPersonList(outPerson, page);
        return page.setRecords(outPersons);
    }

}
