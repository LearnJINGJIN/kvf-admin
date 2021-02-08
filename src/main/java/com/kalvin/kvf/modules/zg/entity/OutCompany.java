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
 * @since 2020-12-17 11:26:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zg_out_company")
public class OutCompany extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 社会统一征信代码
     */
    private String companyCode;

    /**
     * 公司地址
     */
    private String companyAddr;

    /**
     * 法人代表
     */
    private String legalPerson;

    /**
     * 公司经营状态  0-正常经营 1-非正常经营
     */
    private Integer companyStatus;

    /**
     * 公司规模
     */
    private String companySize;

    /**
     * 是否重要外包商
     */
    private Integer imOutsource;
    /**
     * 是否重要外包商
     */
    private Integer isOtc;

    /**
     * 创建人
     */
    private Long createId;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

    /**
     * 修改人
     */
    private Long updateId;

    /**
     * 修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

    /**
     * 公司描述
     */
    private String remarks;

    /**
     * 注册资本
     */
    private String regCapital;

    /**
     * 前5大股东
     */
    private String topShareholders;

    /**
     * 与本机构关联关系
     */
    private Integer relationship;

    /**
     * 已取得的有效资质
     */
    private String effQualification;

    /**
     * 外包服务项目以及内容
     */
    private String proContents;

    /**
     * 对外包服务商提出的风险管理和第三方审计要求
     */
    private String riskAudit;
}
