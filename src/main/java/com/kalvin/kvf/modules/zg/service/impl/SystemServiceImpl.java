package com.kalvin.kvf.modules.zg.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kalvin.kvf.modules.zg.service.SystemService;
import org.springframework.stereotype.Service;
import com.kalvin.kvf.modules.zg.entity.System;
import com.kalvin.kvf.modules.zg.mapper.SystemMapper;

import java.util.List;

/**
 * <p>
 * 系统基本表 服务实现类
 * </p>
 * @since 2020-12-15 17:20:28
 */
@Service
public class SystemServiceImpl extends ServiceImpl<SystemMapper, System> implements SystemService {

    @Override
    public Page<System> listSystemPage(System system) {
        Page<System> page = new Page<>(system.getCurrent(), system.getSize());
        List<System> systems = baseMapper.selectSystemList(system, page);
        return page.setRecords(systems);
    }

    @Override
    public System getInfoById(Long id) {
        return baseMapper.selectInfoById(id);
    }

}
