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
import java.time.LocalDate;

/**
 * <p>
 * 合同-付款对应表
 * </p>
 * @since 2021-01-27 14:44:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zg_pay_contract")
public class PayContract extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 合同编号
     */
    private String contractNo;
    /**
     * 付款编号
     */
    private String payNo;

    /**
     * 付款条件
     */
    private String condPay;

    /**
     * 付款日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate payDate;

    /**
     * 付款金额，单位万
     */
    private BigDecimal payAmount;

    /**
     * 付款比例 %
     */
    private BigDecimal payProport;

    /**
     * 付款说明
     */
    private String payExplain;

}
