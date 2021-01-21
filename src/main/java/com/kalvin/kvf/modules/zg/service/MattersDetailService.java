package com.kalvin.kvf.modules.zg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kalvin.kvf.modules.zg.entity.MattersDetail;

/**
 * <p>
 * 具体事项完成明细表 服务类
 * </p>
 * @since 2021-01-05 16:22:27
 */
public interface MattersDetailService extends IService<MattersDetail> {

    /**
     * 获取列表。分页
     * @param mattersDetail 查询参数
     * @return page
     */
    Page<MattersDetail> listMattersDetailPage(MattersDetail mattersDetail);

}
