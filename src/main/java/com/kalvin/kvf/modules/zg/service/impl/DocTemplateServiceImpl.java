package com.kalvin.kvf.modules.zg.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kalvin.kvf.modules.zg.service.DocTemplateService;
import org.springframework.stereotype.Service;
import com.kalvin.kvf.modules.zg.entity.DocTemplate;
import com.kalvin.kvf.modules.zg.mapper.DocTemplateMapper;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 * @since 2020-12-16 17:34:10
 */
@Service
public class DocTemplateServiceImpl extends ServiceImpl<DocTemplateMapper, DocTemplate> implements DocTemplateService {

    @Override
    public Page<DocTemplate> listDocTemplatePage(DocTemplate docTemplate) {
        Page<DocTemplate> page = new Page<>(docTemplate.getCurrent(), docTemplate.getSize());
        List<DocTemplate> docTemplates = baseMapper.selectDocTemplateList(docTemplate, page);
        return page.setRecords(docTemplates);
    }

}
