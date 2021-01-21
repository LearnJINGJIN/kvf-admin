package com.kalvin.kvf.modules.zg.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kalvin.kvf.modules.zg.service.MattersDetailService;
import org.springframework.stereotype.Service;
import com.kalvin.kvf.modules.zg.entity.MattersDetail;
import com.kalvin.kvf.modules.zg.mapper.MattersDetailMapper;

import java.util.List;

/**
 * <p>
 * 具体事项完成明细表 服务实现类
 * </p>
 * @since 2021-01-05 16:22:27
 */
@Service
public class MattersDetailServiceImpl extends ServiceImpl<MattersDetailMapper, MattersDetail> implements MattersDetailService {

    @Override
    public Page<MattersDetail> listMattersDetailPage(MattersDetail mattersDetail) {
        Page<MattersDetail> page = new Page<>(mattersDetail.getCurrent(), mattersDetail.getSize());
        List<MattersDetail> mattersDetails = baseMapper.selectMattersDetailList(mattersDetail, page);
        return page.setRecords(mattersDetails);
    }

}
