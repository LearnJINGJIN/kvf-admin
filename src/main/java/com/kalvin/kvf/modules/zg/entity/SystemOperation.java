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
 * 系统操作管理
 * </p>
 * @since 2020-12-25 09:50:57
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zg_system_operation")
public class SystemOperation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 所属系统id
     */
    private Long sysId;
    /**
     * 所属系统名称
     */
    private String sysName;

    /**
     * 应用启动步骤
     */
    private String appStart;

    /**
     * 应用停止步骤
     */
    private String appStop;
    /**
     * 数据库启动步骤
     */
    private String dbStart;

    /**
     * 数据库停止步骤
     */
    private String dbStop;

    /**
     * web后管启动步骤
     */
    private String webStart;

    /**
     * web后管停止步骤
     */
    private String webStop;

    /**
     * 前置启动步骤
     */
    private String preStart;

    /**
     * 前置停止步骤
     */
    private String preStop;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 0-正常 1-已删除
     */
    private Integer dr;

    /**
     * 
     */
    private Long createUser;

    /**
     * 
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

    /**
     * 
     */
    private Long updateUser;

    /**
     * 
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

}
