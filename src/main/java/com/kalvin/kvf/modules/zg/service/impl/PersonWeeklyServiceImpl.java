package com.kalvin.kvf.modules.zg.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kalvin.kvf.modules.zg.service.PersonWeeklyService;
import org.springframework.stereotype.Service;
import com.kalvin.kvf.modules.zg.entity.PersonWeekly;
import com.kalvin.kvf.modules.zg.mapper.PersonWeeklyMapper;

import java.util.List;

/**
 * <p>
 * 驻场外包人员周报管理 服务实现类
 * </p>
 * @since 2021-01-21 17:22:57
 */
@Service
public class PersonWeeklyServiceImpl extends ServiceImpl<PersonWeeklyMapper, PersonWeekly> implements PersonWeeklyService {

    @Override
    public Page<PersonWeekly> listPersonWeeklyPage(PersonWeekly personWeekly) {
        Page<PersonWeekly> page = new Page<>(personWeekly.getCurrent(), personWeekly.getSize());
        List<PersonWeekly> personWeeklys = baseMapper.selectPersonWeeklyList(personWeekly, page);
        return page.setRecords(personWeeklys);
    }

}
