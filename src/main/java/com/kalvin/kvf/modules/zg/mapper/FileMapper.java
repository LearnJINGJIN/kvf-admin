package com.kalvin.kvf.modules.zg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kalvin.kvf.modules.zg.entity.File;

import java.util.List;

/**
 * <p>
 * 附件管理 Mapper 接口
 * </p>
 * @since 2021-01-20 14:57:31
 */
public interface FileMapper extends BaseMapper<File> {

    /**
     * 查询列表(分页)
     * @param file 查询参数
     * @param page 分页参数
     * @return list
     */
    List<File> selectFileList(File file, IPage page);

}
