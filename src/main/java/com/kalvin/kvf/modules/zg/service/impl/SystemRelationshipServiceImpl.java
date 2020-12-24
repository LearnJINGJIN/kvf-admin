package com.kalvin.kvf.modules.zg.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kalvin.kvf.modules.zg.service.SystemRelationshipService;
import org.springframework.stereotype.Service;
import com.kalvin.kvf.modules.zg.entity.SystemRelationship;
import com.kalvin.kvf.modules.zg.mapper.SystemRelationshipMapper;

import java.util.List;

/**
 * <p>
 * 系统访问关系 服务实现类
 * </p>
 * @since 2020-12-23 14:52:34
 */
@Service
public class SystemRelationshipServiceImpl extends ServiceImpl<SystemRelationshipMapper, SystemRelationship> implements SystemRelationshipService {

    @Override
    public Page<SystemRelationship> listSystemRelationshipPage(SystemRelationship systemRelationship) {
        Page<SystemRelationship> page = new Page<>(systemRelationship.getCurrent(), systemRelationship.getSize());
        List<SystemRelationship> systemRelationships = baseMapper.selectSystemRelationshipList(systemRelationship, page);
        return page.setRecords(systemRelationships);
    }

    @Override
    public SystemRelationship getEditById(Long id) {
        return baseMapper.selectInfoById(id);
    }

}
