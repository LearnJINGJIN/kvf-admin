package com.kalvin.kvf.modules.zg.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.kalvin.kvf.common.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 项目/需求管理
 * </p>
 * @since 2020-12-29 11:22:59
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zg_project")
public class Project extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 项目/需求名称
     */
    private String projectName;

    /**
     * 项目/需求等级
     */
    private Integer projectLevel;

    /**
     * 申请人id
     */
    private Long infoId;

    /**
     * 申请时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate infoDate;

    /**
     * 业务流程描述
     */
    private String proDesc;

    /**
     * 现有业务流程描述
     */
    private String existProDesc;

    /**
     * 相关具体业务流程描述
     */
    private String relaProDesc;

    /**
     * 期望完成日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expectDate;

    /**
     * 实际完成日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate actualDate;

    /**
     * 附件地址
     */
    private String acceFile;

    /**
     * 所属阶段(1-需求、2-立项、3-招标、4-合同、5-开发、6-测试、7-环境申请、8-上线，9-验收)
     */
    private Integer status;

    /**
     * 需求变更次数
     */
    private Integer numChanges;

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
