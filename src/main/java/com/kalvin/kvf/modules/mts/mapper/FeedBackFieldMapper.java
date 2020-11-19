package com.kalvin.kvf.modules.mts.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kalvin.kvf.modules.mts.entity.FeedBackField;

import java.util.List;

/**
 * Description:外汇局反馈错误查詢保存
 *
 * @Auther: jingjin
 * @Date: 2020-10-19 16:03
 * @Modified by:
 */
public interface FeedBackFieldMapper extends BaseMapper<FeedBackField> {

     List<FeedBackField> getFeedBackField(String inputDate, String dataType);
}
