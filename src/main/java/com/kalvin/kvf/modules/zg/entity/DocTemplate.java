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
 * 
 * </p>
 * @since 2020-12-16 17:34:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zg_doc_template")
public class DocTemplate extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 文档名称
     */
    private String docName;

    /**
     * 文档上传地址
     */
    private String docAddr;

    /**
     * 所属阶段：1-需求阶段 2-立项阶段 3-招标阶段 4-采购阶段 5-开发阶段 6-测试阶段 7-上线阶段 8-验收阶段 9-评价阶段
     */
    private Integer stageStatus;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 删除标志位
     */
    private Integer dr;

    /**
     * 创建人
     */
    private Long createUser;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

    /**
     * 修改人
     */
    private Long updateUser;

    /**
     * 修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

}
