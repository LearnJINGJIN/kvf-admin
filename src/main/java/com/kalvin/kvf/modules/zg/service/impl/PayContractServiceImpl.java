package com.kalvin.kvf.modules.zg.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kalvin.kvf.modules.zg.service.PayContractService;
import org.springframework.stereotype.Service;
import com.kalvin.kvf.modules.zg.entity.PayContract;
import com.kalvin.kvf.modules.zg.mapper.PayContractMapper;

import java.util.List;

/**
 * <p>
 * 合同-付款对应表 服务实现类
 * </p>
 * @since 2021-01-27 14:44:04
 */
@Service
public class PayContractServiceImpl extends ServiceImpl<PayContractMapper, PayContract> implements PayContractService {

    @Override
    public Page<PayContract> listPayContractPage(PayContract payContract) {
        Page<PayContract> page = new Page<>(payContract.getCurrent(), payContract.getSize());
        List<PayContract> payContracts = baseMapper.selectPayContractList(payContract, page);
        return page.setRecords(payContracts);
    }

}
