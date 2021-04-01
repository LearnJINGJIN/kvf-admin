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
 * 外包人员离场登记表
 * </p>
 * @since 2021-03-16 11:21:39
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zg_out_leaveperson")
public class OutLeaveperson extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 登记编码
     */
    private String registerCode;
    private String theme;

    /**
     * 外包人员id
     */
    private Long personId;
    /**
     * 外包人员名字
     */
    private String personName;

    /**
     * 离场日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate leaveDate;

    /**
     * 事由
     */
    private String reason;
    /**
     * 出生年月
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    /**
     * 性别
     */
    private Integer sex;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 证件类型
     */
    private Integer documentType;
    /**
     * 证件号码
     */
    private String idNumber;
    /**
     * 请假流程部署id
     */
    private String deploymentId;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 流程状态(0-未提交 1-审批中 2-已审批 3-已驳回)
     */
    private Integer processStatus;

    /**
     * 流程id
     */
    private String processInstanceId;
    /**
     * 项目名称
     */
    private String projectName;

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
