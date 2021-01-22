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
 * 驻场外包人员周报管理
 * </p>
 * @since 2021-01-21 17:22:57
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zg_person_weekly")
public class PersonWeekly extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 外包人员id
     */
    private Long personId;

    /**
     * 报告日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate reportDate;

    /**
     * 本周工作内容
     */
    private String weekWork;

    /**
     * 下周工作重点
     */
    private String weekFocus;

    /**
     * 类型(1-需求 2-维护 3-培训)
     */
    private Integer type;

    /**
     * 简述
     */
    private String paper;

    /**
     * 涉及系统
     */
    private String involveSystem;

    /**
     * 提出时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate putDate;

    /**
     * 需求结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    /**
     * 计划完成日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate planEndDate;

    /**
     * 目前状态(1-需求讨论 2-开发中 3-sit测试 4-uat测试 5-已上线)
     */
    private Integer status;

    /**
     * 目前进度
     */
    private String progress;

    /**
     * 存在风险
     */
    private String risk;

    /**
     * 备注
     */
    private String remarks;

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
