package com.kalvin.kvf.modules.zg.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.modules.zg.entity.OutOtc;

import java.util.Map;

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
    /**
     * 获取列表。分页
     * @param outOtc 查询参数
     * @return page
     */
    Page<OutOtc> listOtcLeavePage(OutOtc outOtc);

    @Override
    Map<String, Object> getMap(Wrapper<OutOtc> queryWrapper);


    OutOtc getInfoById(Long id);
    /**
     *通过流程id获取对应值
     */

    OutOtc getInfoByProcess(String processInstanceId);
    /**
     * 开始流程
     */
    R startForm(OutOtc outOtc);
}
