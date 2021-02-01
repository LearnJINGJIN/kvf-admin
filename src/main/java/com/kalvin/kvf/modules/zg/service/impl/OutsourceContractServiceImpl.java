package com.kalvin.kvf.modules.zg.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kalvin.kvf.modules.zg.service.OutsourceContractService;
import org.springframework.stereotype.Service;
import com.kalvin.kvf.modules.zg.entity.OutsourceContract;
import com.kalvin.kvf.modules.zg.mapper.OutsourceContractMapper;

import java.util.List;

/**
 * <p>
 * 合同管理 服务实现类
 * </p>
 * @since 2021-01-26 10:36:16
 */
@Service
public class OutsourceContractServiceImpl extends ServiceImpl<OutsourceContractMapper, OutsourceContract> implements OutsourceContractService {

    @Override
    public Page<OutsourceContract> listOutsourceContractPage(OutsourceContract outsourceContract) {
        Page<OutsourceContract> page = new Page<>(outsourceContract.getCurrent(), outsourceContract.getSize());
        List<OutsourceContract> outsourceContracts = baseMapper.selectOutsourceContractList(outsourceContract, page);
        return page.setRecords(outsourceContracts);
    }

    @Override
    public OutsourceContract getOutsourceContractByid(Long id) {
        return baseMapper.selectOutsourceContractByid(id);
    }

}
