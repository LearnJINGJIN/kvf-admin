package com.kalvin.kvf.modules.zg.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kalvin.kvf.modules.zg.entity.SystemDb;
import com.kalvin.kvf.modules.zg.mapper.SystemDbMapper;
import com.kalvin.kvf.modules.zg.service.SystemDbService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统数据库环境管理 服务实现类
 * </p>
 * @since 2020-12-24 16:56:21
 */
@Service
public class SystemDbServiceImpl extends ServiceImpl<SystemDbMapper, SystemDb> implements SystemDbService {

    @Override
    public Page<SystemDb> listSystemDbPage(SystemDb systemDb) {
        Page<SystemDb> page = new Page<>(systemDb.getCurrent(), systemDb.getSize());
        List<SystemDb> systemDbs = baseMapper.selectSystemDbList(systemDb, page);
        return page.setRecords(systemDbs);
    }
    @Override
    public SystemDb getInfoById(Long id) {
        return baseMapper.selectInfoById(id);
    }
}
