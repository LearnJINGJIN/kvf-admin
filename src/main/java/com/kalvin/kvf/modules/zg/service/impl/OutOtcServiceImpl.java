package com.kalvin.kvf.modules.zg.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kalvin.kvf.modules.zg.service.OutOtcService;
import org.springframework.stereotype.Service;
import com.kalvin.kvf.modules.zg.entity.OutOtc;
import com.kalvin.kvf.modules.zg.mapper.OutOtcMapper;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 * @since 2021-01-14 15:13:47
 */
@Service
public class OutOtcServiceImpl extends ServiceImpl<OutOtcMapper, OutOtc> implements OutOtcService {

    @Override
    public Page<OutOtc> listOutOtcPage(OutOtc outOtc) {
        Page<OutOtc> page = new Page<>(outOtc.getCurrent(), outOtc.getSize());
        List<OutOtc> outOtcs = baseMapper.selectOutOtcList(outOtc, page);
        return page.setRecords(outOtcs);
    }

    @Override
    public OutOtc getInfoById(Long id) {
        return baseMapper.selectInfoById(id);
    }
}
