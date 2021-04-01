package com.kalvin.kvf.modules.zg.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kalvin.kvf.modules.zg.service.TransferListService;
import org.springframework.stereotype.Service;
import com.kalvin.kvf.modules.zg.entity.TransferList;
import com.kalvin.kvf.modules.zg.mapper.TransferListMapper;

import java.util.List;

/**
 * <p>
 * 交接清单表 服务实现类
 * </p>
 * @since 2021-03-16 11:41:47
 */
@Service
public class TransferListServiceImpl extends ServiceImpl<TransferListMapper, TransferList> implements TransferListService {

    @Override
    public Page<TransferList> listTransferListPage(TransferList transferList) {
        Page<TransferList> page = new Page<>(transferList.getCurrent(), transferList.getSize());
        List<TransferList> transferLists = baseMapper.selectTransferListList(transferList, page);
        return page.setRecords(transferLists);
    }

}
