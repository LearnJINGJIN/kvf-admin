package com.kalvin.kvf.modules.zg.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.kalvin.kvf.common.entity.BaseEntity;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 外包请假管理
 * </p>
 * @since 2021-03-03 15:05:11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zg_out_applyleave")
public class OutApplyleave extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 外包人员id
     */
    private Long personId;

    /**
     * 请假类型(1-病假 2-事假 3-年假 4-婚假 5-产假 6-丧假 7-探亲假 8-护理假 9-其它)
     */
    private Integer leaveType;

    /**
     * 请假事由
     */
    private String leaveReason;
    /**
     * 请假流程部署id
     */
    private String deploymentId;

    /**
     * 请假开始时间
     */
     private String startTime;

    /**
     * 请假结束时间
     */
     private String endTime;

    /**
     * 请假总天数
     */
    private BigDecimal days;

    /**
     * 应急委托人
     */
    private String emergencyContact;
    /**
     * 姓名
     */
    private String personName;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 联系电话
     */
    private String phone;

    /**
     * 流程id
     */
    private String processInstanceId;

    private String theme;

    /**
     * 流程状态(0-申请 1-审批中 2-已完成)
     */
    private Integer processStatus;

    /**
     * 
     */
    private Long createUser;

    /**
     * 
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 
     */
    private Long updateUser;

    /**
     * 
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

}
