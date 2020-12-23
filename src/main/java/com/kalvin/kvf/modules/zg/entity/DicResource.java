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
 * @since 2020-12-17 10:26:21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zg_dic_resource")
public class DicResource extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 资源代码
     */
    private String reCode;

    /**
     * 资源名称  类似：svn，云桌面，安全管理助手
     */
    private String reName;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 删除标志位  0-正常 1-已删除
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
     * 修改日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

}
