package com.kalvin.kvf.modules.zg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kalvin.kvf.modules.zg.entity.File;

/**
 * <p>
 * 附件管理 服务类
 * </p>
 * @since 2021-01-20 14:57:31
 */
public interface FileService extends IService<File> {

    /**
     * 获取列表。分页
     * @param file 查询参数
     * @return page
     */
    Page<File> listFilePage(File file);

}
