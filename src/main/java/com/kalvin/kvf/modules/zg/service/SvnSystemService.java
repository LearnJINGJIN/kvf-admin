package com.kalvin.kvf.modules.zg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kalvin.kvf.modules.zg.entity.SvnSystem;

/**
 * <p>
 *  服务类
 * </p>
 * @since 2020-12-15 14:48:46
 */
public interface SvnSystemService extends IService<SvnSystem> {

    /**
     * 获取列表。分页
     * @param svnSystem 查询参数
     * @return page
     */
    Page<SvnSystem> listSvnSystemPage(SvnSystem svnSystem);
    SvnSystem getInfoById(Long id);
}
