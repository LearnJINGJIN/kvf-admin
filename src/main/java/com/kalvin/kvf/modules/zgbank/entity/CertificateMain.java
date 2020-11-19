package com.kalvin.kvf.modules.zgbank.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.kalvin.kvf.common.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.xml.bind.annotation.XmlElement;
import java.time.LocalDateTime;

/**
 * <p>
 * 证书管理表
 * </p>
 * @since 2020-09-28 16:23:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zgbank_certificate_main")
public class CertificateMain extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 证书名称
     */
    private String certificateName;

    /**
     * 证书类型
     */
    private Integer certificateType;

    /**
     * 证书相关使用设备
     */
    private String certificateAboutMachine;

    /**
     * 使用开始时间yyyy-MM-dd
     */
    private String certificateStartTime;

    /**
     * 使用结束时间yyyy-MM-dd
     */
    private String certificateEndTime;

    /**
     * 管理人员id
     */
    private Long userId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 短信发送次数
     */
    private Integer sendMsgTimes;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
