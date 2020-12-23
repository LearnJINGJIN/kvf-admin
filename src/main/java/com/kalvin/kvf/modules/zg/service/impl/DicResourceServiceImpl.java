package com.kalvin.kvf.modules.zg.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kalvin.kvf.modules.zg.service.DicResourceService;
import org.springframework.stereotype.Service;
import com.kalvin.kvf.modules.zg.entity.DicResource;
import com.kalvin.kvf.modules.zg.mapper.DicResourceMapper;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 * @since 2020-12-17 10:26:21
 */
@Service
public class DicResourceServiceImpl extends ServiceImpl<DicResourceMapper, DicResource> implements DicResourceService {

    @Override
    public Page<DicResource> listDicResourcePage(DicResource dicResource) {
        Page<DicResource> page = new Page<>(dicResource.getCurrent(), dicResource.getSize());
        List<DicResource> dicResources = baseMapper.selectDicResourceList(dicResource, page);
        return page.setRecords(dicResources);
    }

}
