package com.kalvin.kvf.modules.zg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kalvin.kvf.modules.zg.entity.DocTemplate;

/**
 * <p>
 *  服务类
 * </p>
 * @since 2020-12-16 17:34:10
 */
public interface DocTemplateService extends IService<DocTemplate> {

    /**
     * 获取列表。分页
     * @param docTemplate 查询参数
     * @return page
     */
    Page<DocTemplate> listDocTemplatePage(DocTemplate docTemplate);

}
