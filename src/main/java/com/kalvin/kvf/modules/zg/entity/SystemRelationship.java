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
 * 系统访问关系
 * </p>
 * @since 2020-12-23 14:52:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zg_system_relationship")
public class SystemRelationship extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private String useDes;

    /**
     * 系统应用环境id
     */
    private Long configId;

    /**
     * 源系统名称
     */
    private String sysName;
    /**
     *  源系统ip
     */
    private String appIp;

    /**
     * 1-主动访问 2-被访问
     */
    private Integer callType;

    /**
     * 端口
     */
    private String port;

    /**
     * 目标系统应用关系id
     */
    private Long targetConfigId;
    /**
     * 目标系统ip
     */
    private String targetAppIp;
    /**
     *目标系统名称
     */
    private String targetSysName;
    /**
     * 是否在用(0-正常 1-已删除)
     */
    private Integer isUse;
    /**
     * 是否在用(on-正常 off-禁用)
     */
    private String status;

    /**
     * 使用人
     */
    private Long userId;
    /**
     * 使用人名称
     */
    private String userName;

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
