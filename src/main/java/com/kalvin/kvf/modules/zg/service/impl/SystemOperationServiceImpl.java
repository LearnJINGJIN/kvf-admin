package com.kalvin.kvf.modules.zg.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kalvin.kvf.modules.zg.entity.SystemOperation;
import com.kalvin.kvf.modules.zg.mapper.SystemOperationMapper;
import com.kalvin.kvf.modules.zg.service.SystemOperationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统操作管理 服务实现类
 * </p>
 * @since 2020-12-25 09:50:57
 */
@Service
public class SystemOperationServiceImpl extends ServiceImpl<SystemOperationMapper, SystemOperation> implements SystemOperationService {

    @Override
    public Page<SystemOperation> listSystemOperationPage(SystemOperation systemOperation) {
        Page<SystemOperation> page = new Page<>(systemOperation.getCurrent(), systemOperation.getSize());
        List<SystemOperation> systemOperations = baseMapper.selectSystemOperationList(systemOperation, page);
        return page.setRecords(systemOperations);
    }

    @Override
    public SystemOperation getInfoById(Long id) {
        return baseMapper.selectInfoById(id);
    }

}
