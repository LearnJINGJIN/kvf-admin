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
     * 外包类型
     */
    private Integer outsourceType;

    /**
     * 采购人月
     */
    private Integer personMonth;

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
     * 外包人员名称
     */
    private String personName;
}
