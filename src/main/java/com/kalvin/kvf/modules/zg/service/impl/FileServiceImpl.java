package com.kalvin.kvf.modules.zg.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kalvin.kvf.modules.zg.service.FileService;
import org.springframework.stereotype.Service;
import com.kalvin.kvf.modules.zg.entity.File;
import com.kalvin.kvf.modules.zg.mapper.FileMapper;

import java.util.List;

/**
 * <p>
 * 附件管理 服务实现类
 * </p>
 * @since 2021-01-20 14:57:31
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

    @Override
    public Page<File> listFilePage(File file) {
        Page<File> page = new Page<>(file.getCurrent(), file.getSize());
        List<File> files = baseMapper.selectFileList(file, page);
        return page.setRecords(files);
    }

}
