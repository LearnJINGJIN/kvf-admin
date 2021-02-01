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
 * 项目任务清单
 * </p>
 * @since 2021-01-14 17:26:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zg_outsource_job")
public class OutsourceJob extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 项目id
     */
    private Long proId;
    /**
     * 项目名称
     */
    private String proName;

    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 任务名称
     */
    private String jobCode;

    /**
     * 优先级(0-较低 1-最低 2-普通 3-较高 4-最高)
     */
    private Integer priority;

    /**
     * 任务负责人id
     */
    private Long headId;
    /**
     * 任务负责人
     */
    private String headName;

    /**
     * 开始日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    /**
     * 截止日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    /**
     * 任务状态(0-未开始 1-进行中 2-已完成)
     */
    private Integer jobStatus;

    /**
     * 描述
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
