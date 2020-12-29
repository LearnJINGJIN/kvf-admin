package com.kalvin.kvf.modules.zg.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kalvin.kvf.modules.zg.service.SvnSystemService;
import org.springframework.stereotype.Service;
import com.kalvin.kvf.modules.zg.entity.SvnSystem;
import com.kalvin.kvf.modules.zg.mapper.SvnSystemMapper;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 * @since 2020-12-15 14:48:46
 */
@Service
public class SvnSystemServiceImpl extends ServiceImpl<SvnSystemMapper, SvnSystem> implements SvnSystemService {

    @Override
    public Page<SvnSystem> listSvnSystemPage(SvnSystem svnSystem) {
        Page<SvnSystem> page = new Page<>(svnSystem.getCurrent(), svnSystem.getSize());
        List<SvnSystem> svnSystems = baseMapper.selectSvnSystemList(svnSystem, page);
        return page.setRecords(svnSystems);
    }

    @Override
    public SvnSystem getInfoById(Long id) {
        return baseMapper.selectInfoById(id);
    }
}
