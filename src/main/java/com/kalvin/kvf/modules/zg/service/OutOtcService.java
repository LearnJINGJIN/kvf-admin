package com.kalvin.kvf.modules.zg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kalvin.kvf.modules.zg.entity.OutOtc;

/**
 * <p>
 *  服务类
 * </p>
 * @since 2021-01-14 15:13:47
 */
public interface OutOtcService extends IService<OutOtc> {

    /**
     * 获取列表。分页
     * @param outOtc 查询参数
     * @return page
     */
    Page<OutOtc> listOutOtcPage(OutOtc outOtc);

    OutOtc getInfoById(Long id);
}
