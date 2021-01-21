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
import java.time.Year;

/**
 * <p>
 * 年度外包计划表
 * </p>
 * @since 2021-01-05 10:54:44
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zg_out_plan")
public class OutPlan extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 年度
     */
    @DateTimeFormat(pattern = "yyyy")
    private Year planYear;

    /**
     * 事项
     */
    private String matters;

    /**
     * 推进时间计划
     */
    private String advancePlan;

    /**
     * 工作流程
     */
    private String workProcess;
    /**
     * 备注
     */
    private String remarks;

    /**
     * 状态(0-推进中 1-已完成)
     */
    private Integer status;

    /**
     * 
     */
    private Long createUser;

    /**
     * CURRENT_TIMESTAMP
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

    /**
     * 
     */
    private Long updateUser;

    /**
     * CURRENT_TIMESTAMP
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

    /**
     * 删除标志位（0-正常 1-已删除）
     */
    private Integer dr;

}
