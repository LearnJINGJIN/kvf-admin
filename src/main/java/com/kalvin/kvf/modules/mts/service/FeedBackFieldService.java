package com.kalvin.kvf.modules.mts.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kalvin.kvf.modules.mts.entity.FeedBackField;

import java.util.List;

/**
 * Description:
 *
 * @Auther: jingjin
 * @Date: 2020-10-22 17:15
 * @Modified by:
 */
public interface FeedBackFieldService extends IService<FeedBackField> {
    List<FeedBackField> getFeedBackField(String inputDate, String dataType);
}
