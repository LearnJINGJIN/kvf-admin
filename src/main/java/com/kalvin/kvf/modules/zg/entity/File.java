package com.kalvin.kvf.modules.zg.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.kalvin.kvf.common.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

/**
 * <p>
 * 附件管理
 * </p>
 * @since 2021-01-20 14:57:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zg_file")
public class File extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 附件类别
     */
    private String fileType;
    /**
     * 类别（job_任务附件 contract-合同附件 pay-付款附件）
     */
    private String type;

    /**
     * 附件名称
     */
    private String fileName;

    /**
     * 附件地址
     */
    private String fileAddrs;
    /**
     * 附件大小
     */
    private String fileSize;

    /**
     * 
     */
    private Long relevanceId;

    /**
     * 上传时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime uploadTime;

    /**
     * 上传人
     */
    private Long userId;
    /**
     * 上传人名称
     */
    private String userName;

}
