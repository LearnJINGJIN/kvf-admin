package com.kalvin.kvf.modules.zg.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kalvin.kvf.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 * @since 2021-01-14 15:13:47
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zg_out_otc")
public class OutOtc extends BaseEntity {

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
     * 合同编号
     */
    private String contractNo;
    /**
     * 流程编号
     */
    private String deploymentId;
    /**
     * 外包类型
     */
    private Integer outsourceType;

    /**
     * 采购人月
     */
    private Integer personMonth;
    private OutPerson outPerson;
    /**
     * 入场时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    /**
     * 计划工作时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate planDate;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 已支付
     */
    private BigDecimal havePay;

    /**
     * 剩余待支付
     */
    private BigDecimal pendPay;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String remarks;
    /**
     * 角色
     */
    private String outRole;

    /**
     * 创建人
     */
    private Long createUser;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

    /**
     * 更新人
     */
    private Long updateUser;


    /**
     * 更新日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

    /**
     * 删除标志位
     */
    private Integer dr;
    /**
     * 流程状态 0-未提交 1-审批中 2-已完成
     */
    private Integer processStatus;
    /**
     * 流程id
     */
    private String processInstanceId;
    /**
     * 外包人员名称
     */
    private String personName;
    /**
     * 行方工作对接人员
     */
    private Long bankPerson;
    /**
     * 行方工作对接人员
     */
    private String bankName;

    /**
     * 所属公司
     */
    private String companyName;

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
     * 邮箱
     */
    private String email;

    /**
     * 关联所属公司
     */
    private Long companyId;

    /**
     * 证件类型
     */
    private Integer documentType;
    /**
     * 证件号码
     */
    private String idNumber;
    /**
     * 表单主题
     */
    private String theme;

}
