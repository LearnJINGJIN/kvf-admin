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
 * 系统应用环境管理
 * </p>
 * @since 2020-12-22 11:29:38
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zg_system_app")
public class SystemApp extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
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
     * 应用环境类别(1-dev 2-sit 3-uat 4-prod)
     */
    private Integer configType;

    /**
     * 应用ip
     */
    private String appIp;

    /**
     * 账号/密码，多个以“，”区分
     */
    private String ipAccount;

    /**
     * 开发语言（1-java 2-c 3-c++ 4-ios 5-android 6-php 7-python 8-sql）
     */
    private Integer devLanguage;

    /**
     * 操作系统类型(1-linux 2-windows 3-unix 4-mac )
     */
    private Integer operSysType;

    /**
     * 操作系统版本号
     */
    private String operSysVersion;

    /**
     * 是否包含后官平台（0-是 1-否）
     */
    private Integer isWeb;

    /**
     * 后管url地址
     */
    private String webUrl;

    /**
     * 后管账号/密码
     */
    private String webAccount;

    /**
     * 备注
     */
    private String remark;

    /**
     * 0-正常 1-删除
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
