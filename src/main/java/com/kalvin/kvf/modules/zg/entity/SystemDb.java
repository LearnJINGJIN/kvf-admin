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
 * 系统数据库环境管理
 * </p>
 * @since 2020-12-24 16:56:21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zg_system_db")
public class SystemDb extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long sysId;
    /**
     * 系统名称
     */
    private String sysName;

    /**
     * 应用环境类别(1-dev 2-sit 3-uat 4-prod)
     */
    private Integer configType;

    /**
     * 数据库ip
     */
    private String dbIp;

    /**
     * 数据账号/密码
     */
    private String dbAccount;

    /**
     * 数据库类型(1-mysql 2-oracle 3-db2)
     */
    private Integer dbType;

    /**
     * 数据库版本
     */
    private String dbVersion;

    /**
     * 1-linux 2-windows 3-unix 4-mac
     */
    private Integer operSysType;

    /**
     * 操作系统版本号
     */
    private String operSysVersion;

    /**
     * 操作系统账号/密码
     */
    private String operAccount;

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
