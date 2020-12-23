package com.kalvin.kvf.modules.zg.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kalvin.kvf.modules.zg.entity.SystemApp;
import com.kalvin.kvf.modules.zg.mapper.SystemAppMapper;
import com.kalvin.kvf.modules.zg.service.SystemAppService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统应用环境管理 服务实现类
 * </p>
 * @since 2020-12-22 11:29:38
 */
@Service
public class SystemAppServiceImpl extends ServiceImpl<SystemAppMapper, SystemApp> implements SystemAppService {

    @Override
    public Page<SystemApp> listSystemAppPage(SystemApp systemApp) {
        Page<SystemApp> page = new Page<>(systemApp.getCurrent(), systemApp.getSize());
        List<SystemApp> systemApps = baseMapper.selectSystemAppList(systemApp, page);
        return page.setRecords(systemApps);
    }
    @Override
    public SystemApp getInfoById(Long id) {
        return baseMapper.selectInfoById(id);
    }

}
