package com.kalvin.kvf.modules.mts.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kalvin.kvf.modules.mts.entity.FeedBackField;
import com.kalvin.kvf.modules.mts.mapper.FeedBackFieldMapper;
import com.kalvin.kvf.modules.mts.service.FeedBackFieldService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 *
 * @Auther: jingjin
 * @Date: 2020-10-22 17:16
 * @Modified by:
 */
@Service
public class FeedBackFieldServiceImpl extends ServiceImpl<FeedBackFieldMapper, FeedBackField> implements FeedBackFieldService {
    @Override
    public List<FeedBackField> getFeedBackField(String inputDate, String dataType) {
        return baseMapper.getFeedBackField(inputDate, dataType);
    }
}
